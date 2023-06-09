package com.loci.colist.view.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loci.colist.R
import com.loci.colist.databinding.ActivitySettingBinding
import com.loci.colist.service.PriceForegroundService

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_setting)
        val view = binding.root
        setContentView(view)

        binding.startForeground.setOnClickListener {
            Toast.makeText(this, "start",Toast.LENGTH_LONG).show()
            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "START"
            startService(intent)
        }

        binding.stopForeground.setOnClickListener {
            Toast.makeText(this,"stop",Toast.LENGTH_LONG).show()
            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "STOP"
            startService(intent)
        }
    }
}