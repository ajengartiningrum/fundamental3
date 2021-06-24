package com.dicoding.picodiploma.myappsubmis2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.view.View
import androidx.lifecycle.Observer
import android.widget.Toast
import android.provider.Settings
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel
    private val list = ArrayList<UserItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Github User Search"

        showRecycleView()
    }

    private fun showRecycleView(){
        adapter = UserAdapter(list)
        adapter.notifyDataSetChanged()

        item_recycle_view.layoutManager = LinearLayoutManager(this)
        item_recycle_view.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.getUser().observe(this, Observer { UserItems ->
            if ((UserItems != null)) {
                adapter.setData(UserItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(userItems: UserItems) {
                showDetail(userItems)
            }
        })
    }

    private fun showDetail(userItems: UserItems) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, userItems)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading(true)

                if (query != null) {
                    mainViewModel.setUser(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }
    private fun showLoading(state: Boolean){
        if (state) {
            progres_bar.visibility = View.VISIBLE
        }else{
            progres_bar.visibility = View.GONE
        }
    }
}