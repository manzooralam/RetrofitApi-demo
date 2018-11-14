package restaurent.manzoor.restaurentapp.slider

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

import java.util.ArrayList

import restaurent.manzoor.restaurentapp.fragment.FragmentSlider

class SliderPagerAdapter(fm: FragmentManager, frags: List<Fragment>) : FragmentStatePagerAdapter(fm) {

    internal var mFrags: List<Fragment> = ArrayList()

    init {
        mFrags = frags
    }

    override fun getItem(position: Int): Fragment {
        val index = position % mFrags.size
        Log.d(TAG, "position: $position")
        Log.d(TAG, "index: $index")
        return FragmentSlider.newInstance(mFrags[index].arguments!!.getString("params")!!)
    }

    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    companion object {

        private val TAG = "SliderPagerAdapter"
    }
}
