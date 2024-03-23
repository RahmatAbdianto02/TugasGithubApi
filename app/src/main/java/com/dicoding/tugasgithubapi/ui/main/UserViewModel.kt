package com.dicoding.tugasgithubapi.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.tugasgithubapi.data.api.ApiConfig
import com.dicoding.tugasgithubapi.data.response.GithubResponse
import com.dicoding.tugasgithubapi.data.response.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel(){
    private val _listUser = MutableLiveData<List<ItemsItem>>()

    private val _isLoading = MutableLiveData<Boolean>()

    companion object {
        private const val TAG = "MainActivity"
    }

    init {

        setReviewData("")
    }
    fun setReviewData(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(query)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value =response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false

                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}