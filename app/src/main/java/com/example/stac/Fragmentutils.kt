package com.example.stac


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import java.util.ArrayList

class FragmentUtils(internal val containerId: Int, internal var fragments: ArrayList<Fragment>) {
    var currentFragment: Fragment? = null
    var currentIndex: Int = 0
    val fragmentCount: Int = fragments.size


    fun getFragmentByPosition(position: Int, args: Bundle?): Fragment {
        if (position < 0 || position >= fragmentCount)
            throw IllegalArgumentException("position invalid")
        return fragments[position]
    }

    fun setCurrentFragmentByPosition(fm: FragmentManager, position: Int, args: Bundle?) {
        try {
            var args = args
            currentIndex = position
            val ft = fm.beginTransaction()
            val tag = getFragmentTagByPosition(position)
            var fragment: Fragment? = fm.findFragmentByTag(tag)
            if (fragment == null) {
                if (args == null) args = Bundle()
                fragment = getFragmentByPosition(position, args)
                ft.add(containerId, fragment, tag)
            } else {
                if (currentFragment !== fragment) {
                    ft.show(fragment)
                }
            }
            postUpdateFragment(fm, ft, fragment, position)
            currentFragment = fragment
        } catch (e: Exception) {
            // Current Fragment Not initialized
        }
    }

    private fun postUpdateFragment(fm: FragmentManager,
                                   ft: FragmentTransaction, fragment: Fragment,
                                   position: Int) {
        hideFragmentExcept(fm, ft, position)
        fragment.setMenuVisibility(true)
        fragment.userVisibleHint = true
        ft.commitNowAllowingStateLoss()
    }

    private fun hideFragmentExcept(fm: FragmentManager,
                                   ft: FragmentTransaction,
                                   position: Int) {
        var fragment: Fragment? = null
        for (i in 0 until fragmentCount) {
            if (i != position) {
                fragment = fm.findFragmentByTag(getFragmentTagByPosition(i))
                if (fragment != null) {
                    ft.hide(fragment)
                    // todo
                    // fragment.setMenuVisibility(false);
                    fragment.userVisibleHint = false
                }
            }
        }
    }

    private fun getFragmentTagByPosition(position: Int): String {
        if (position < 0 || position >= fragmentCount)
            throw IllegalArgumentException("position is invalid.")

        return position.toString() + fragments[position].javaClass.simpleName
    }
}