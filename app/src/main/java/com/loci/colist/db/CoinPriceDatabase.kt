package com.loci.colist.db

import android.content.Context
import androidx.room.*
import com.loci.colist.db.dao.InterestCoinDAO
import com.loci.colist.db.dao.SelectedCoinPriceDAO
import com.loci.colist.db.entity.DateConverters
import com.loci.colist.db.entity.InterestCoinEntity
import com.loci.colist.db.entity.SelectedCoinPriceEntity


@Database(entities = [InterestCoinEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DateConverters::class)
abstract class CoinPriceDatabase : RoomDatabase() {

    abstract fun interestCoinDAO(): InterestCoinDAO
    abstract fun selectedCoinDAO(): SelectedCoinPriceDAO

    companion object {
        @Volatile
        private var INSTANCE: CoinPriceDatabase? = null

        fun getDatabase(
            context: Context
        ): CoinPriceDatabase {
            return INSTANCE ?: synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}