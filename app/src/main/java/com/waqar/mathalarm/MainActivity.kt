package com.waqar.mathalarm

import Adapters.SectionStatePagerAdapter
import Fragments.AlarmFragment
import Fragments.StopwatchFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var adapter = SectionStatePagerAdapter(supportFragmentManager)
        adapter.addFragment(AlarmFragment(),"AlarmFragment")
        adapter.addFragment(StopwatchFragment(),"StopwatchFragment")

        vp_main.adapter = adapter
    }

//    fun fragmentTransaction(fragment: Fragment, fragmentName: String) {
//        val fragmentManager = supportFragmentManager
//
//
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(
//            android.R.anim.slide_in_left, android.R.anim.slide_out_right
//        );
//        fragmentTransaction.replace(R.id., fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }

}
