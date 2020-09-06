package com.utildev.jetpack.presentation.fragment.auth

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utildev.jetpack.R
import com.utildev.jetpack.presentation.base.BaseAdapter

class RoleAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager,
    adapterListener: AdapterListener
) : BaseAdapter(R.layout.item_role, 0, 0, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position]
            holder.binding.setVariable(BR.role, item)
            holder.binding.executePendingBindings()
        }
    }
}