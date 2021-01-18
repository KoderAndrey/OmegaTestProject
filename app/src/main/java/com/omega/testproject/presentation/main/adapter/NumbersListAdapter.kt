package com.omega.testproject.presentation.main.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omega.testproject.databinding.NumberItemBinding


class NumbersListAdapter: RecyclerView.Adapter<NumbersListAdapter.UserViewHolder>() {

    val listNums: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            NumberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        listNums.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listNums[position], position)
    }


    inner class UserViewHolder(private val binding: NumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            num: String,
            position: Int
        ) {
            binding.value = num
            binding.numberField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    listNums[position] = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
    }


    fun setItems(listNumbers: List<String>) {
        this.listNums.run {
            clear()
            addAll(listNumbers)
        }
        notifyDataSetChanged()
    }

    fun insertItem() {
        if (listNums.size < 20) {
            listNums.add("")
            notifyItemInserted(listNums.size - 1)
        }
    }

    fun deleteItem() {
        if (listNums.size > 2) {
            listNums.removeAt(listNums.size - 1)
            notifyItemRemoved(listNums.size)
        }
    }

}