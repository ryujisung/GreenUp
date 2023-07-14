package com.example.greenup.ui.viewpager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.greenup.R
import com.example.greenup.ui.viewpager.SecondFragment
import com.example.greenup.ui.viewpager.ThirdFragment

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: ViewPagerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val indicatorvi = findViewById<CircleIndicator>(R.id.indicator)
        viewPager = findViewById(R.id.viewPager)
        adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                indicatorvi.selectDot(p0)
            }

        })
        indicatorvi.createDotPanel(3, R.drawable.square_dot_off, R.drawable.square_dot_on, 0)

    }

    private inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FirstFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }
}
