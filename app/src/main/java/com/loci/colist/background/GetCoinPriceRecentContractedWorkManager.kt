package com.loci.colist.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.loci.colist.db.entity.SelectedCoinPriceEntity
import com.loci.colist.network.model.RecentCoinPriceList
import com.loci.colist.repository.DBRepository
import com.loci.colist.repository.NetWorkRepository
import timber.log.Timber
import java.util.*

// 최근 거래된 코인 가격 내역을 가져오는 WorkManager

class GetCoinPriceRecentContractedWorkManager(
    val context: Context, workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val dbRepository = DBRepository()
    private val netWorkRepository = NetWorkRepository()


    override suspend fun doWork(): Result {

        Timber.d("doWork")

        getAllInterestSelectedCoinData()

        return Result.success()
    }

    // 1. 관심있어하는 코인리스트를 가져와서
    // 2. 관심있는 코인 각각의 가격 변동 정보를 가져와서 (New API)
    // 3. 관심있는 코인 각각의 가격 변동 정보 DB에 저장

    suspend fun getAllInterestSelectedCoinData() {
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        for (coinData in selectedCoinList) {

            Timber.d(coinData.toString())

            val timeStamp = Calendar.getInstance().time

            val recentCoinPriceList = netWorkRepository.getRecentCoinPrice(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())

            saveSelectedCoinPrice(coinData.coin_name, recentCoinPriceList, timeStamp)
        }

    }

    fun saveSelectedCoinPrice(
        coinName: String,
        recentCoinPriceList: RecentCoinPriceList,
        timeStamp: Date
    ) {
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp
        )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)
    }

}
















