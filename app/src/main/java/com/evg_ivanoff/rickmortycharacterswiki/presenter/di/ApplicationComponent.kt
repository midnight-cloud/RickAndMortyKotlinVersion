package com.evg_ivanoff.rickmortycharacterswiki.presenter.di

import android.app.Application
import android.content.Context
import com.evg_ivanoff.rickmortycharacterswiki.presenter.MainActivity
import com.evg_ivanoff.rickmortycharacterswiki.presenter.fragments.DetailFragment
import com.evg_ivanoff.rickmortycharacterswiki.presenter.fragments.MainFragment
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@ActivityScope
@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)

    fun context(): Context
    fun applicationContext(): Application
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope