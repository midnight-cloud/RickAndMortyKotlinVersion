package com.evg_ivanoff.rickmortycharacterswiki.presenter.di

import android.app.Application
import android.content.Context
import com.evg_ivanoff.rickmortycharacterswiki.presenter.CharInfoActivity
import com.evg_ivanoff.rickmortycharacterswiki.presenter.MainActivity
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@ActivityScope
@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: CharInfoActivity)

    fun context(): Context
    fun applicationContext(): Application
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope