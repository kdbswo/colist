package com.loci.colist.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.loci.colist.R
import timber.log.Timber

class IntroActivity : AppCompatActivity() {

    private  val viewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()
    }
}