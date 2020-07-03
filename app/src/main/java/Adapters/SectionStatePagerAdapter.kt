package Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SectionStatePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var fragments : MutableList<Fragment> = mutableListOf()
    var fragmentsName : MutableList<String> = mutableListOf()

    public fun addFragment(fragment: Fragment,name : String){
        fragments.add(fragment)
        fragmentsName.add(name)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}