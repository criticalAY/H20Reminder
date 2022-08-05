package com.criticalay.h20reminder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.ui.fragments.HistoryFragment
import com.criticalay.h20reminder.ui.fragments.HomeFragment
import com.criticalay.h20reminder.ui.fragments.SettingFragment
import com.criticalay.h20reminder.ui.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)


        setUpTabs()

    }

    private fun setUpTabs() {
        val adapter= ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(HistoryFragment(),"History")
        adapter.addFragment(SettingFragment(),"Settings")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_show_chart)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_settings)


    }
}