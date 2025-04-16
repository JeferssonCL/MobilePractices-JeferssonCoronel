package com.example.projectmaps.data.dagger

import android.content.Context
import com.example.projectmaps.data.context.AppDatabase
import com.example.projectmaps.data.local.RouteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideRouteDao(appDatabase: AppDatabase): RouteDao {
        return appDatabase.routeDao()
    }
}
