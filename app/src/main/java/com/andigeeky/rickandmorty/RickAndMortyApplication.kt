package com.andigeeky.rickandmorty

import android.app.Application
import com.andigeeky.rickandmorty.characters.di.common_modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@RickAndMortyApplication)
            modules(common_modules)
        }
    }
}