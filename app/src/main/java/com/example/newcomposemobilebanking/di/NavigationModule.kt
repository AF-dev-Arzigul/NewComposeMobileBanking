package com.example.newcomposemobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.newcomposemobilebanking.navigation.AppNavigationManager
import com.example.newcomposemobilebanking.navigation.AppNavigator
import com.example.newcomposemobilebanking.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: AppNavigationManager): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: AppNavigationManager): NavigationHandler
}