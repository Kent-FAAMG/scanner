package com.bureau.qr.host

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.chuckerteam.chucker.api.ChuckerInterceptor

@HiltAndroidApp
class HostApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Chuck for HTTP debugging
        // Chuck will automatically intercept all HTTP calls when in debug mode
    }
} 