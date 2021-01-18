package com.omega.testproject

import android.app.Application
import com.omega.testproject.domain.di.AppInjector

class NumberApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.setup(this)
    }
}