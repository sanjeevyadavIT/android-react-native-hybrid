package com.sanjeevyadavit.react

import android.app.Activity
import android.os.Bundle
import com.facebook.react.ReactDelegate
import com.facebook.react.ReactNativeHost
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
}