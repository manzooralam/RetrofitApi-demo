package restaurent.manzoor.restaurentapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import restaurent.manzoor.restaurentapp.R
import restaurent.manzoor.restaurentapp.activity.LoginActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun goSignInActivity(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
