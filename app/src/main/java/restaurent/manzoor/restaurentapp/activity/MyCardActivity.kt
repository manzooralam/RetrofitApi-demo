package restaurent.manzoor.restaurentapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_my_card.*
import kotlinx.android.synthetic.main.my_card_toolbar.*
import restaurent.manzoor.restaurentapp.adapter.MyCartAdapter
import restaurent.manzoor.restaurentapp.R

class MyCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_card)
        setSupportActionBar(my_card_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "My Cart "

        my_CartRV.layoutManager = GridLayoutManager(this, 1) as RecyclerView.LayoutManager?
        my_card_toolbar.setNavigationOnClickListener {
            finish()
        }
        getData()
    }
    private fun getData() {

        var list  = ArrayList<String>()

        for (i in 1..10){
            list.add(i.toString())
        }

        my_CartRV.adapter = MyCartAdapter(this, list)
    }
    fun goToCheckOutActivity(view: View){
        startActivity(Intent(this, CheckOutActivity::class.java))
    }
}

