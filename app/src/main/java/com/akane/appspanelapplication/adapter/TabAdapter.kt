package com.akane.appspanelapplication.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akane.appspanelapplication.ActusFragment
import com.akane.appspanelapplication.InfosFragment
import com.akane.appspanelapplication.InscriptionFragment

@Suppress("DEPRECATION")
internal class TabAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ActusFragment()
            }
            1 -> {
                InfosFragment()
            }
            2 -> {
                InscriptionFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}