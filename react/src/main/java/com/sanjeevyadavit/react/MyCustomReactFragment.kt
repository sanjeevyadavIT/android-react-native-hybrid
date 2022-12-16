package com.sanjeevyadavit.react

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.facebook.react.ReactFragment
import com.facebook.react.ReactNativeHost
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.FragmentComponent
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MyCustomReactFragment: ReactFragment() {

    @Inject
    protected lateinit var _rnHost: ReactNativeHost

    var mReactDelegate: CustomReactDelegate? = null

    companion object {
        private const val ARG_COMPONENT_NAME = "arg_component_name"
        private const val ARG_LAUNCH_OPTIONS = "arg_launch_options"
        const val ARG_DATA = "data"
        private const val KEY_REFERRER = "referrer"


        private fun createBundleData(extra: String?) = Bundle().apply {
            extra?.let {
                val jsonObject = JSONObject(extra)
                putString(ARG_DATA, jsonObject.toString())
            }
        }


        fun newInstance(componentName: String, referrer: String? = null): MyCustomReactFragment {
            val bundle = Bundle().apply {
                putString(ARG_COMPONENT_NAME, componentName)
                putBundle(ARG_LAUNCH_OPTIONS, createBundleData("{\"hello\": true}"))
                putString(KEY_REFERRER, referrer)
            }
            return MyCustomReactFragment().apply { arguments = bundle }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mainComponentName: String = "reactNative"
        var launchOptions: Bundle? = null
        if (getArguments() != null) {
            mainComponentName = getArguments()?.getString(ARG_COMPONENT_NAME) ?: "reactNative"
            launchOptions = getArguments()?.getBundle(ARG_LAUNCH_OPTIONS)
        }
        mReactDelegate =
            CustomReactDelegate(getActivity(), reactNativeHost, mainComponentName, launchOptions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mReactDelegate!!.loadApp()
        return mReactDelegate!!.reactRootView
    }

    override fun getReactNativeHost(): ReactNativeHost = _rnHost
}