package com.utildev.jetpack.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val spacing: Int,
    private val spanCount: Int,
    private val includeEdge: Boolean
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

        }
        if (position >= spanCount) {
            outRect.top = spacing
        }
    }
}