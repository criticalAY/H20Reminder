package com.criticalay.h20reminder.ui.fragments.adapters

import android.icu.text.CaseMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.criticalay.h20reminder.ui.fragments.HistoryFragment
import com.criticalay.h20reminder.ui.fragments.HomeFragment
import com.criticalay.h20reminder.ui.fragments.SettingFragment

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(
    supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    ///////////////////////////////////////////Two tabs count/////////////////////////////////////////////////////
    override fun getCount(): Int {
        return  mFragmentList.size

    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String){

        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }


}