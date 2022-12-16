package com.sanjeevyadavit.hybridsetup

import android.app.Application
import com.sanjeevyadavit.react.ReactUtils
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject
    protected lateinit var reactUtils: ReactUtils

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch(Dispatchers.Main) {
            initReact()
        }
    }

    private fun initReact() {
        applicationScope.launch(Dispatchers.IO) {
            reactUtils.initialiseSoLoader()
        }
    }
}