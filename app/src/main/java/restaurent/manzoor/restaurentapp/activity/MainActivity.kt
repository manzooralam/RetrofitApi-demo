package restaurent.manzoor.restaurentapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.home_toolbar.*
import restaurent.manzoor.restaurentapp.R
import restaurent.manzoor.restaurentapp.adapter.CategoryAdapter
import restaurent.manzoor.restaurentapp.fragment.FragmentSlider
import restaurent.manzoor.restaurentapp.retrofitnetwork.RetrofitDataProvider
import restaurent.manzoor.restaurentapp.slider.SliderIndicator
import restaurent.manzoor.restaurentapp.slider.SliderPagerAdapter


class MainActivity(private var retrofitDataProvider: RetrofitDataProvider? = null) : AppCompatActivity(){

    var mAdapter: SliderPagerAdapter?= null
    var mIndicator: SliderIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(home_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, home_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        setupSlider()

        latestRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        bestsellerRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        getData()
        latestViewAll.setOnClickListener {
            startActivity(Intent(this, ViewAllActivity::class.java)
                    .putExtra("name", "Latest"))
        }
        bestsellerViewAll.setOnClickListener {
            startActivity(Intent(this, ViewAllActivity::class.java)
                    .putExtra("name", "BestSeller"))
        }


    }

    private fun getData() {

        var list  = ArrayList<String>()

        for (i in 1..10){
            list.add(i.toString())
        }

        latestRV.adapter = CategoryAdapter(this, list)
        bestsellerRV.adapter = CategoryAdapter(this, list)
    }

    private fun setupSlider() {
        sliderView.setDurationScroll(800)
        val fragments = ArrayList<Fragment>()
        fragments.add(FragmentSlider.newInstance("http://www.kifayti.com/image/cache/catalog/banners/CF15EA99-25C2-1D28-AEFC-876ED6D7274E-848x455.jpeg"))
        fragments.add(FragmentSlider.newInstance("http://www.kifayti.com/image/cache/catalog/banners/ssssssssssssssss-848x455.jpg"))
        fragments.add(FragmentSlider.newInstance("http://www.kifayti.com/image/cache/catalog/banners/pexels-photo-761963-848x455.jpeg"))
        fragments.add(FragmentSlider.newInstance("http://www.kifayti.com/image/cache/catalog/banners/ssssssssssssssss-848x455.jpg"))

        mAdapter = SliderPagerAdapter(supportFragmentManager, fragments)
        sliderView.setAdapter(mAdapter)
        mIndicator = SliderIndicator(this, pagesContainer, sliderView, R.drawable.indicator_circle)
        mIndicator!!.setPageCount(fragments.size)
        mIndicator!!.show()
        /*sliderView.requestFocusFromTouch()*/
    }
}
