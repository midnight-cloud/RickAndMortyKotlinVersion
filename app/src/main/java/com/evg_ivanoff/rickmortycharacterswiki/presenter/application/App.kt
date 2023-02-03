package com.evg_ivanoff.rickmortycharacterswiki.presenter.application

import android.app.Application
import android.content.Context
import com.evg_ivanoff.rickmortycharacterswiki.presenter.di.AppModule
import com.evg_ivanoff.rickmortycharacterswiki.presenter.di.ApplicationComponent
import com.evg_ivanoff.rickmortycharacterswiki.presenter.di.DaggerApplicationComponent
import com.google.android.material.color.DynamicColors

class App : Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

val Context.appComponent: ApplicationComponent
get() = when(this) {
    is App -> appComponent
    else -> this.applicationContext.appComponent
}