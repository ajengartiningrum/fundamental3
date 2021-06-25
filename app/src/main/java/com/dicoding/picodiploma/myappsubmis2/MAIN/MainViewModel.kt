package com.dicoding.picodiploma.myappsubmis2.MAIN

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.myappsubmis2.USER.UserItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpClient.log
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<UserItems>>()

    fun setUser(name: String){
        val listItems = ArrayList<UserItems>()
        val url =  "https://api.github.com/search/users?q=$name"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler(){

            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObjects = JSONObject(result)
                    val list = responseObjects.getJSONArray("items")

                    for (i in 0 until list.length()){
                        val item = list.getJSONObject(i)
                        val userItems =
                            UserItems(
                                avatar = null,
                                username = null
                            )
                        userItems.avatar = item.getString("avatar_url")
                        userItems.username = item.getString("login")

                        listItems.add(userItems)
                    }
                    listUsers.postValue(listItems)
                }catch (e: Exception){
                    log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                log.d("onFailure", error?.message.toString())
            }
        })

        }
    fun getUser() : LiveData<ArrayList<UserItems>>{
        return listUsers
    }

    }