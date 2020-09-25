package com.utildev.jetpack.presentation.fragment.auth

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utildev.jetpack.R
import com.utildev.jetpack.domain.model.response.role.RoleItem
import com.utildev.jetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_role.view.*

class RoleAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager,
    private val adapterListener: AdapterListener
) : BaseAdapter(R.layout.item_role, 0, 0, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item: RoleItem = items[position] as RoleItem
            holder.binding.setVariable(BR.role, item)
            holder.binding.executePendingBindings()
            val v = holder.binding.root
            v.itRole_tvTitle.isChecked = item.selected
            v.itRole_tvTitle.setOnClickListener {
                v.itRole_tvTitle.toggle()
                item.selected = v.itRole_tvTitle.isChecked
                adapterListener.onItemClick(item, position)
            }
        }
    }

    fun resetRolesState() {
        items.forEach { (it as RoleItem).selected = false }
        notifyDataSetChanged()
    }
}