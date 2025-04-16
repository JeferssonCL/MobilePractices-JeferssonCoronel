package com.example.projectmaps.data.dagger

import com.example.projectmaps.data.local.RouteDao
import com.example.projectmaps.data.repository.RouteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRouteRepository(routeDao: RouteDao): RouteRepository {
        return RouteRepository(routeDao)
    }
}
