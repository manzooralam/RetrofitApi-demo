package restaurent.manzoor.restaurentapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import restaurent.manzoor.restaurentapp.R

class MyAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_address)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true);
        supportActionBar?.title="My Address"
    }
}
