package org.sopt.soptseminar.util

import androidx.recyclerview.widget.RecyclerView

interface ItemStartDragListener {
    fun onStartDrag(viewHolder : RecyclerView.ViewHolder)
}