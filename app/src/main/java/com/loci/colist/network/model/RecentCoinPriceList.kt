package com.loci.colist.network.model

import com.loci.colist.dataModel.RecentPriceData

data class RecentCoinPriceList (
    val status : String,
    val data : List<RecentPriceData>
)