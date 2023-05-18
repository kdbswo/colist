package com.loci.colist.network

import com.loci.colist.network.model.CurrentPriceList
import com.loci.colist.network.model.RecentCoinPriceList
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList

//    https://api.bithumb.com/public/transaction_history/BTC_KRW
    @GET("public/transaction_history/{coin}_KRW")
    suspend fun getRecentCoinPrice(@Path("coin") coin : String) : RecentCoinPriceList
}