package id.gits.gitsmvvmkotlin.mvvm.splashscreen

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import id.ac.unpad.profolio.util.ext.navigatorImplicit
import id.gits.gitsmvvmkotlin.R

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        Handler().postDelayed({
            navigatorImplicit(this@SplashscreenActivity,
                    "id.co.gits.movies.main.MainActivity")
        }, 2000)
    }
}
