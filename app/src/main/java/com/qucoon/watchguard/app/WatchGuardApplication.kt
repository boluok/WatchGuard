package com.qucoon.watchguard.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.qucoon.watchguard.modules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchGuardApplication :MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WatchGuardApplication)
            modules(appModules)
        }
    }
    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}