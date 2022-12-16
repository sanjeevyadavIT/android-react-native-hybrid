package com.sanjeevyadavit.react

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frameLayout = createFrameLayout()
        setContentView(frameLayout)

        loadReactFragment(frameLayout)
    }

    private fun createFrameLayout() =
        FrameLayout(this).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

    private fun loadReactFragment(view: FrameLayout) {
        supportFragmentManager.commit {
            add(
                view.id,
                MyCustomReactFragment.newInstance(
                    "reactNative" /* This string should be same as used in AppRegistry in index.js */
                )
            )
        }
    }

    override fun invokeDefaultOnBackPressed() {
        TODO("Not yet implemented")
    }
}