package com.loci.colist.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.loci.colist.MainActivity
import com.loci.colist.R
import com.loci.colist.databinding.ActivityIntroBinding
import timber.log.Timber

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private  val viewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()
        viewModel.first.observe(this, Observer {
            if(it){
                //처음 접속x
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                //처음 접속
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })
    }
}