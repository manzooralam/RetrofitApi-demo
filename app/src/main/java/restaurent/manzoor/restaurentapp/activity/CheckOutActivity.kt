package restaurent.manzoor.restaurentapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import restaurent.manzoor.restaurentapp.activity.MyAddressActivity
import restaurent.manzoor.restaurentapp.R

class CheckOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Check Out "
        supportActionBar!!.setDisplayUseLogoEnabled(true)
    }
    fun goToMyAddressActivity(view: View){
        startActivity(Intent(this, MyAddressActivity::class.java))
    }
}
