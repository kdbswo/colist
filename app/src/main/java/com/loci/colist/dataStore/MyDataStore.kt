package com.loci.colist.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loci.colist.App

class MyDataStore {

    private val context = App.context()

    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    }

    private val mDataStore : DataStore<Preferences> = context.dataStore

    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")

    // 메인액티비티로 갈 때 True 라고 변경
    // 처음 접속하는 유저면 -> true
    // 처음 접속이 아니면 -> false
   suspend fun setupFirstData(){
        mDataStore.edit { preferences -> preferences[FIRST_FLAG] = true }
   }

   suspend fun getFirstData(): Boolean{
       var currentValue = false

       mDataStore.edit { preferences ->
           currentValue = preferences[FIRST_FLAG] ?: false }

       return currentValue
   }


}


















