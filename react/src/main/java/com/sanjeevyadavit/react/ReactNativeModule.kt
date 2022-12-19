package com.sanjeevyadavit.react

import android.app.Application
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.ReactPackageTurboModuleManagerDelegate
import com.facebook.react.bridge.*
import com.facebook.react.fabric.ComponentFactory
import com.facebook.react.fabric.CoreComponentsRegistry
import com.facebook.react.fabric.FabricJSIModuleProvider
import com.facebook.react.fabric.ReactNativeConfig
import com.facebook.react.shell.MainReactPackage
import com.facebook.react.uimanager.ViewManagerRegistry
import com.sanjeevyadavit.react.newarchitecture.components.MainComponentsRegistry
import com.sanjeevyadavit.react.newarchitecture.modules.MainApplicationTurboModuleManagerDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReactNativeModule {

    @Provides
    @Singleton
    fun provideReactNativeHost(application: Application): ReactNativeHost {

        if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
            /**
             * A {@link ReactNativeHost} that helps you load everything needed for the New Architecture, both
             * TurboModule delegates and the Fabric Renderer.
             *
             * <p>Please note that this class is used ONLY if you opt-in for the New Architecture (see the
             * `newArchEnabled` property). Is ignored otherwise.
             */
            return object : ReactNativeHost(application) {
                override fun getUseDeveloperSupport(): Boolean {
                    return BuildConfig.DEBUG
                }

                override fun getPackages(): List<ReactPackage?>? {
                    val packages: MutableList<ReactPackage> = mutableListOf()

                    // Packages that cannot be autolinked yet can be added manually here, for example:
                    //     packages.add(new MyReactNativePackage());
                    // TurboModules must also be loaded here providing a valid TurboReactPackage implementation:
                    //     packages.add(new TurboReactPackage() { ... });
                    // If you have custom Fabric Components, their ViewManagers should also be loaded here
                    // inside a ReactPackage.
                    packages.add(MainReactPackage())

                    return packages
                }

                override fun getReactPackageTurboModuleManagerDelegateBuilder(): ReactPackageTurboModuleManagerDelegate.Builder {
                    // Here we provide the ReactPackageTurboModuleManagerDelegate Builder. This is necessary
                    // for the new architecture and to use TurboModules correctly.
                    return MainApplicationTurboModuleManagerDelegate.Builder()
                }

                override fun getJSMainModuleName(): String? {
                    return "index"
                }

                override fun getJSIModulePackage(): JSIModulePackage? {
                    return object : JSIModulePackage {
                        override fun getJSIModules(
                            reactApplicationContext: ReactApplicationContext,
                            jsContext: JavaScriptContextHolder
                        ): List<JSIModuleSpec<UIManager>> {
                            val specs: MutableList<JSIModuleSpec<UIManager>> = mutableListOf()

                            // Here we provide a new JSIModuleSpec that will be responsible of providing the
                            // custom Fabric Components.
                            specs.add(
                                object : JSIModuleSpec<UIManager> {
                                    override fun getJSIModuleType(): JSIModuleType {
                                        return JSIModuleType.UIManager
                                    }

                                    override fun getJSIModuleProvider(): JSIModuleProvider<UIManager> {
                                        // Here we register a Components Registry.
                                        // The one that is generated with the template contains no components
                                        // and just provides you the one from React Native core.
                                        val componentFactory = ComponentFactory()
                                        CoreComponentsRegistry.register(componentFactory)

                                        // Here we register a Components Registry.
                                        // The one that is generated with the template contains no components
                                        // and just provides you the one from React Native core.
                                        MainComponentsRegistry.register(componentFactory)
                                        val reactInstanceManager: ReactInstanceManager =
                                            getReactInstanceManager()
                                        val viewManagerRegistry = ViewManagerRegistry(
                                            reactInstanceManager.getOrCreateViewManagers(
                                                reactApplicationContext
                                            )
                                        )
                                        return FabricJSIModuleProvider(
                                            reactApplicationContext,
                                            componentFactory,
                                            ReactNativeConfig.DEFAULT_CONFIG,
                                            viewManagerRegistry
                                        )
                                    }
                                })
                            return specs
                        }
                    }
                }

            }
        } else {
            return object : ReactNativeHost(application) {
                override fun getUseDeveloperSupport(): Boolean {
                    return BuildConfig.DEBUG
                }

                override fun getPackages(): List<ReactPackage> {
                    return listOf<ReactPackage>(
                        MainReactPackage()
                    )
                }

                override fun getJSMainModuleName(): String {
                    return "index"
                }

            }
        }

    }
}