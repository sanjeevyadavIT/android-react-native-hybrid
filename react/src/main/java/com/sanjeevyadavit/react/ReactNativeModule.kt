package com.sanjeevyadavit.react

import android.app.Application
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
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