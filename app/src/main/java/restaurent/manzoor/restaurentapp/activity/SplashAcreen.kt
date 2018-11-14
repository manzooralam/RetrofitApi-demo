package restaurent.manzoor.restaurentapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import restaurent.manzoor.restaurentapp.model.StopModel
import restaurent.manzoor.restaurentapp.R
import restaurent.manzoor.restaurentapp.retrofitnetwork.RetrofitDataProvider


class SplashAcreen: AppCompatActivity() {
var retrofitDataProvider: RetrofitDataProvider?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ctivity_splash)

        retrofitDataProvider = RetrofitDataProvider((this))
        val timer = object : Thread() {
            override fun run() {
                try {
                    //Display for 3 seconds
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    // TODO: handle exception
                    e.printStackTrace()
                } finally {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    callApi()
                }
            }
        }
        timer.start()

    }
  //callApi called here from finally body of splach screen
    private fun callApi() {
      startActivity(Intent(this@SplashAcreen, MainActivity::class.java))
      finishAffinity()
    }
}