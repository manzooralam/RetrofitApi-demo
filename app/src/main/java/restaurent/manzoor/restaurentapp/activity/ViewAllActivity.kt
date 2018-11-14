package restaurent.manzoor.restaurentapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import restaurent.manzoor.restaurentapp.adapter.CategoryAdapter
import restaurent.manzoor.restaurentapp.adapter.MyCartAdapter
import kotlinx.android.synthetic.main.activity_viewall.*
import kotlinx.android.synthetic.main.common_toolbar.*
import restaurent.manzoor.restaurentapp.R
import restaurent.manzoor.restaurentapp.adapter.ViewAllAdapter
import java.util.ArrayList

class ViewAllActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewall)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.getStringExtra("name")

        viewallRV.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        toolbar.setNavigationOnClickListener {
            finish()
        }

        getData()
        //view chnages
        val imageNo1 = findViewById(R.id.view) as ImageView
        imageNo1.setTag(1)
        imageNo1.setOnClickListener {
            if (imageNo1.getTag()==1) {
                imageNo1.setImageResource(R.drawable.ic_view_change1)
                viewallRV.layoutManager = GridLayoutManager(this, 2)
                imageNo1.setTag(2)
            } else {
                imageNo1.setImageResource(R.drawable.ic_view_change)
                viewallRV.layoutManager = GridLayoutManager(this, 1)
                imageNo1.setTag(1)
            }
        }
    }

    private fun getData() {

        var list  = ArrayList<String>()

        for (i in 1..10){
            list.add(i.toString())
        }

        viewallRV.adapter = ViewAllAdapter(this, list)
    }
}