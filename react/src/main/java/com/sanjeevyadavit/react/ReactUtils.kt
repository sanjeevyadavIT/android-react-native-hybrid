package com.sanjeevyadavit.react

import android.app.Application
import android.util.Log
import com.facebook.react.ReactNativeHost
import com.facebook.react.config.ReactFeatureFlags
import com.facebook.soloader.SoLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReactUtils @Inject constructor(
    private val application: Application,
    private val reactNativeHost: ReactNativeHost
){
    private val initialised = AtomicBoolean(false)

    suspend fun initialiseSoLoader(){
        if (initialised.get()) return

        withContext(Dispatchers.IO) {
            try {
                // If you opted-in for the New Architecture, we enable the TurboModule system
                ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
                SoLoader.init(application, false)
            } catch (e: IllegalStateException) {
                e.message?.let { Log.e("REACT ERROR", it) }
            }
        }
        initialised.set(true)
    }

    suspend fun createReactContextInBackground() {
        try {
            if (initialised.get().not())
                initialiseSoLoader()
            reactNativeHost.reactInstanceManager.createReactContextInBackground()
        } catch (e: Throwable) {
            e.message?.let { Log.e("REACT ERROR", it) }
        }
    }
}