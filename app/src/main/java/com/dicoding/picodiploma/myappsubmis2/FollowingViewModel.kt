package com.dicoding.picodiploma.myappsubmis2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class FollowingViewModel : ViewModel() {
    val following = MutableLiveData<ArrayList<FollowingItems>>()

    fun setUser(name: String?){
        val listItems = ArrayList<FollowingItems>()
        val url = "https://api.github.com/users/$name/following"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object  : AsyncHttpResponseHandler(){

            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try{
                    val result = String(responseBody)
                    val responseArray = JSONArray(result)

                    for (i in 0 until responseArray.length()) {
                        val following = responseArray.getJSONObject(i)
                        val followingItems = FollowingItems(avatar = null, username = null)
                        followingItems.username = following.getString("login")
                        followingItems.avatar = following.getString("avatar_url")
                        listItems.add(followingItems)
                    }
                    following.postValue(listItems)
                }catch (e: Exception){
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }
    fun getUser() : LiveData<ArrayList<FollowingItems>> {
        return following
    }
}