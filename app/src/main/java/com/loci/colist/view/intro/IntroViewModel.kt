package com.loci.colist.view.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loci.colist.dataStore.MyDataStore
import kotlinx.coroutines.launch
import timber.log.Timber

class IntroViewModel : ViewModel() {

    fun checkFirstFlag() = viewModelScope.launch {

        val getData = MyDataStore().getFirstData()

        Timber.d(getData.toString())
    }

}