package com.example.projectmaps.data.dagger

import com.example.projectmaps.data.repository.RouteRepository
import com.example.projectmaps.ui.map.MapsActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MapsActivity)
}
