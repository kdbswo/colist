package com.loci.colist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.loci.colist.view.main.MainActivity
import com.loci.colist.databinding.ActivitySelectBinding
import com.loci.colist.view.adapter.SelectRVAdapter
import timber.log.Timber

// https://api.bithumb.com/public/ticker/ALL_KRW \
class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding

    private val viewModel: SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

        binding.laterTextArea.setOnClickListener {

            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)

        }

        viewModel.save.observe(this, Observer {
            if (it.equals("done")) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        })

    }
}





