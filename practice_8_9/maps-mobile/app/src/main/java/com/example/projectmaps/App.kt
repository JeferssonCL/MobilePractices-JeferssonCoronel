package com.example.projectmaps

import android.app.Application
import com.example.projectmaps.data.dagger.AppComponent
import com.example.projectmaps.data.dagger.DaggerAppComponent
import com.example.projectmaps.data.dagger.DatabaseModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}
