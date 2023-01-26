package com.example.newcomposemobilebanking.util


import org.orbitmvi.orbit.ContainerHost

interface AppViewModel<INTENT : Any, STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT> {
    fun onEventDispatcher(intent: INTENT)
}
