package com.dicoding.picodiploma.myappsubmis2

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_followers.*


class FollowersFragment : Fragment() {
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var adapter: FollowersAdapter
    private val list = ArrayList<FollowersItems>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configFollowers()

    }

    private fun configFollowers(){
    adapter = FollowersAdapter(list)
        adapter.notifyDataSetChanged()

        rv_followers.setHasFixedSize(true)
        rv_followers.layoutManager = LinearLayoutManager(activity)
        rv_followers.adapter = adapter
        adapter.getItemCount()
        adapter.notifyDataSetChanged()

        val intent = activity?.intent?.getParcelableExtra<UserItems>(DetailActivity.EXTRA_USER) as UserItems
        val getusername = intent.username

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(FollowersViewModel::class.java)

        followersViewModel.setUser(name = getusername)

        followersViewModel.getUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setData(it)
            }
        })
    }
}