package com.loci.colist.repository

import com.loci.colist.network.Api
import com.loci.colist.network.RetrofitInstance

class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getRecentCoinPrice(coin: String) = client.getRecentCoinPrice(coin)
}