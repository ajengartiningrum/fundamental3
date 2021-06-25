package com.dicoding.picodiploma.myappsubmis2.FAVOURITE

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.myappsubmis2.R
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class FavoriteAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteAdapter.NoteViewHolder>() {
    var Favorites = ArrayList<Favorite>()
    set(Favorites) {
        if (Favorites.size > 0) {
            this.Favorites.clear()
        }
        this.Favorites.addAll(Favorites)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(Favorites[position])
    }

    override fun getItemCount(): Int = this.Favorites.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fav: Favorite) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(fav.avatar)
                        .apply(RequestOptions().override(250, 250))
                        .into(itemView.img_avatar)
                txt_username.text = fav.username
                txt_name.text = fav.name
                txt_company.text = fav.company.toString()

            }
        }
    }
}