package com.example.newcomposemobilebanking.di

import com.example.newcomposemobilebanking.screen.splash.SplashNavigation
import com.example.newcomposemobilebanking.screen.splash.SplashNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*
 * Arzigul Nazarbaeva
 * 1/20/2023, Friday, 4:56 PM
*/

@Module
@InstallIn(ViewModelComponent::class)
interface Navigation {

    @Binds
    fun intro(impl: SplashNavigationImpl): SplashNavigation


}