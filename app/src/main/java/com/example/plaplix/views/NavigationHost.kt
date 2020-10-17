package com.example.plaplix.views

import androidx.fragment.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment, boolean: Boolean)
}