package com.example.weathertimeapp.mvp.view.base

import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

open class FragmentView(fragment: Fragment) {
    private val fragmentRef: WeakReference<Fragment> = WeakReference(fragment)

    val fragment: Fragment?
        get() = fragmentRef.get()

    val context: Fragment?
        get() = fragment

    val fragmentManager: androidx.fragment.app.FragmentManager?
        get() {
            val fragment = fragment
            return fragment?.fragmentManager
        }
}
