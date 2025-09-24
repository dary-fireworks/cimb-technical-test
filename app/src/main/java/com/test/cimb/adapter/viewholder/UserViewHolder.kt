package com.test.cimb.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.test.cimb.databinding.ItemHolderUserBinding
import com.test.cimb.model.User

class UserViewHolder(
    private val view: ItemHolderUserBinding,
): RecyclerView.ViewHolder(view.root) {

    fun bind(user: User) {
        view.apply {
            tvName.text = user.name
            tvEmail.text = user.email
        }
    }

}