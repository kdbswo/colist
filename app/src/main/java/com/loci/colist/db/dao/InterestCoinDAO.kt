package com.loci.colist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.loci.colist.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDAO {

    // getAllData
    // Flow - 데이터의 변경사항을 감지하기 좋다
    @Query("SELECT * FROM interest_coin_table")
    fun getAllData(): Flow<List<InterestCoinEntity>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    //update
    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    // getSelectedCoinList -> 내가 관심있어한 코인 데이터 가져오는 것
    // coin1 / coin2 / coin3 -> coin1 data / coin2 data / coin3 data
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedData(selected: Boolean = true): List<InterestCoinEntity>

}