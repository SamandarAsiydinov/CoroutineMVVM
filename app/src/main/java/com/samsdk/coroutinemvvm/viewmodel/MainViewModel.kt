package com.samsdk.coroutinemvvm.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsdk.coroutinemvvm.network.RetroInstance
import com.samsdk.coroutinemvvm.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val imageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    fun getImageObserver(): MutableLiveData<Bitmap> {
        return imageLiveData
    }

    fun makeApiCall(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getImageFromUrl(query)
            val bytes = response.bytes()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            imageLiveData.postValue(bitmap)
        }
    }
}