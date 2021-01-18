package com.omega.testproject.presentation.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omega.testproject.presentation.main.adapter.NumbersListAdapter


@BindingAdapter("data")
fun  setRecyclerViewList(recyclerView: RecyclerView, items: List<String>?) {
    if (recyclerView.adapter is NumbersListAdapter) {
        items?.let { users ->
            (recyclerView.adapter as NumbersListAdapter).setItems(users)
        }
    }
}