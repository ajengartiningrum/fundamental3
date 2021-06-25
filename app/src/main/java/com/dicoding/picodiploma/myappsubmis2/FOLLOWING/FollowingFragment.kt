package com.dicoding.picodiploma.myappsubmis2.FOLLOWING

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.myappsubmis2.DETAIL.DetailActivity
import com.dicoding.picodiploma.myappsubmis2.R
import com.dicoding.picodiploma.myappsubmis2.USER.UserItems
import kotlinx.android.synthetic.main.fragment_following.*


class FollowingFragment : Fragment() {

    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var adapter: FollowingAdapter
    private val list = ArrayList<FollowingItems>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configFollowing()

    }

    private fun configFollowing(){
        adapter =
            FollowingAdapter(list)
        adapter.notifyDataSetChanged()

        rv_following.layoutManager = LinearLayoutManager(activity)
        rv_following.adapter = adapter

        val intent = activity?.intent?.getParcelableExtra<UserItems>(DetailActivity.EXTRA_USER) as UserItems
        val getusername = intent.username

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(FollowingViewModel::class.java)

        followingViewModel.setUser(name = getusername)

        followingViewModel.getUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setData(it)
            }
        })
    }
}