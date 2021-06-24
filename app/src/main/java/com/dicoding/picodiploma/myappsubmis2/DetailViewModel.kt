package com.dicoding.picodiploma.myappsubmis2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import android.util.Log
import androidx.lifecycle.LiveData
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModel : ViewModel() {
    val detail = MutableLiveData<ArrayList<DetailItems>>()

    fun setUser(name: String?) {
        val listItems = ArrayList<DetailItems>()
        val url = "https://api.github.com/users/$name"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val detailItems = DetailItems(avatar = null, name = null, username = null, company = null, location = null)
                    detailItems.avatar = responseObject.getString("avatar_url")
                    detailItems.name = responseObject.getString("name")
                    detailItems.username = responseObject.getString("login")
                    detailItems.company = responseObject.getString("company")
                    detailItems.location = responseObject.getString("location")

                    listItems.add(detailItems)

                    detail.postValue(listItems)

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }
    fun getUser() : LiveData<ArrayList<DetailItems>> {
        return detail
    }
    }