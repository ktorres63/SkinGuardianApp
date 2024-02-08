package com.idnp.skinguardianapp.data.di

import android.content.Context
import androidx.room.Room
import com.idnp.skinguardianapp.data.database.SkinGuardianDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SkinGuardianDataBase::class.java, "SkinGuardianDB").allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideUserDao(database: SkinGuardianDataBase) = database.getUserDao()
}