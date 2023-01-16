package com.example.newcomposemobilebanking.model.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.newcomposemobilebanking.util.SharedPreferenceHelper
import javax.inject.Inject

class LocalStorage @Inject constructor(
    @ApplicationContext context: Context
) : SharedPreferenceHelper(context) {

    var isFirstLaunch: Boolean by booleans(true)
    var isSignedIn: Boolean by booleans(false)
    var accessToken: String by strings()
    var refreshToken: String by strings()

}