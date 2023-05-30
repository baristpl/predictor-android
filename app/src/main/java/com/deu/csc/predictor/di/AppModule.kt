package com.deu.csc.predictor.di

import android.content.Context
import com.deu.csc.predictor.database.AppDao
import com.deu.csc.predictor.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    fun provideDatabaseDao(database: AppDatabase): AppDao {
        return database.databaseDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

}
