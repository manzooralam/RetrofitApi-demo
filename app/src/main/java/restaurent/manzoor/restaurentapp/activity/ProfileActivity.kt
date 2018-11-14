package restaurent.manzoor.restaurentapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.common_toolbar.*
import restaurent.manzoor.restaurentapp.R
import restaurent.manzoor.restaurentapp.common.ClsGeneral
import restaurent.manzoor.restaurentapp.common.ConstantValue
import restaurent.manzoor.restaurentapp.common.Utilz
import restaurent.manzoor.restaurentapp.retrofitnetwork.RetrofitDataProvider

class ProfileActivity : AppCompatActivity() {

    var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Update Profile"

        retrofitDataProvider = RetrofitDataProvider(this)
       // input_phone.setText(ClsGeneral.getPreferences(this, ConstantValue.MOBILE))
         //val intent= getIntent()

        if (getIntent().getStringExtra("fromSplashScreen").equals(ConstantValue.MOBILE)) {

            input_phone.setText(ClsGeneral.getPreferences(this, ConstantValue.MOBILE))
            input_phone.setClickable(false);
            input_phone.setFocusable(false);
            }else  if (getIntent().getStringExtra("fromSplashScreen").equals(ConstantValue.EMAIL)) {

            input_email.setText(ClsGeneral.getPreferences(this, ConstantValue.EMAIL))
            input_name.setText(ClsGeneral.getPreferences(this, ConstantValue.NAME))
            input_email.setClickable(false);
            input_email.setFocusable(false);
        }else  if (getIntent().getStringExtra("fromLoginActivity").equals(ConstantValue.EMAIL)) {

            input_email.setText(ClsGeneral.getPreferences(this, ConstantValue.EMAIL))
            input_name.setText(ClsGeneral.getPreferences(this, ConstantValue.NAME))
            input_email.setClickable(false);
            input_email.setFocusable(false);
        }else  if (getIntent().getStringExtra("fromLoginActivity").equals(ConstantValue.MOBILE)) {

            input_phone.setText(ClsGeneral.getPreferences(this, ConstantValue.MOBILE))
            input_phone.setClickable(false);
            input_phone.setFocusable(false);
        }

        signup.setOnClickListener {
            if (input_name.text.toString().trim().equals("")) Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            else if (input_email.text.toString().trim().equals("")) Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show()
            else if (input_address.text.toString().trim().equals("")) Toast.makeText(this, "Enter your Address", Toast.LENGTH_SHORT).show()
            else {
                if (Utilz.isInternetConnected(this)) {
                    Utilz.showProgress(this)
                    ClsGeneral.setPreferences(this, ConstantValue.EMAIL, input_email.text.toString())
                    ClsGeneral.setPreferences(this, ConstantValue.MOBILE, input_phone.text.toString())
                    startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                    Toast.makeText(this,"Succesfully updated",Toast.LENGTH_LONG).show()
                    finishAffinity()
                }else{
                    Utilz.displayMessageAlert(resources.getString(R.string.nointernetconnection), this)
                }
            }
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

    }
}