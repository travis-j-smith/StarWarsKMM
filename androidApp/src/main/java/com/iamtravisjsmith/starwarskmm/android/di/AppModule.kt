package com.iamtravisjsmith.starwarskmm.android.di

import android.content.Context
import com.iamtravisjsmith.starwarskmm.persistence.DatabaseDriverFactory
import com.iamtravisjsmith.starwarskmm.repositories.StarWarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Singleton
    fun providesDatabaseDriverFactory(@ApplicationContext context: Context): DatabaseDriverFactory {
        return DatabaseDriverFactory(context)
    }

    @Provides
    @Singleton
    fun providesStarWarsRepository(databaseDriverFactory: DatabaseDriverFactory): StarWarsRepository {
        return StarWarsRepository(databaseDriverFactory)
    }
}
