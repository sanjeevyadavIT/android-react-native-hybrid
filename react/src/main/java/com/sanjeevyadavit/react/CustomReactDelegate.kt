package com.sanjeevyadavit.react

import android.app.Activity
import android.os.Bundle
import com.facebook.react.ReactDelegate
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler

class CustomReactDelegate(
    val activity: Activity?,
    val reactNativeHost: ReactNativeHost,
    appKey: String,
    launchOptions: Bundle?
) : ReactDelegate(
    activity,
    reactNativeHost,
    appKey,
    launchOptions
), DefaultHardwareBackBtnHandler {

    override fun createRootView(): ReactRootView {
        val reactRootView = ReactRootView(activity)
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED)
        return reactRootView
    }

    override fun onHostResume() {
        if (reactNativeHost.hasInstance()) {
//            if (activity is DefaultHardwareBackHandler) {
//                reactNativeHost
//                    .reactInstanceManager
//                    .onHostResume(activity, this)
//            } else {
//                throw ClassCastException(
//                    "Host Activity does not implement DefaultHardwareBackHandler"
//                )
//            }
        }
    }

    override fun invokeDefaultOnBackPressed() {
//        (activity as DefaultHardwareBackHandler).invokeDefaultOnBackPressed()
    }

//    override fun isConcurrentRootEnabled(): Boolean {
//        // If you opted-in for the New Architecture, we enable Concurrent Root (i.e. React 18).
//        // More on this on https://reactjs.org/blog/2022/03/29/react-v18.html
//        return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
//    }
}