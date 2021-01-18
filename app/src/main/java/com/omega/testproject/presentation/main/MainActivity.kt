package com.omega.testproject.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omega.testproject.R
import com.omega.testproject.databinding.ActivityMainBinding
import com.omega.testproject.presentation.ResultType
import com.omega.testproject.presentation.main.adapter.NumbersListAdapter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val model: NumberViewModel by inject()
    private lateinit var adapter: NumbersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = model
        binding.lifecycleOwner = this
        adapter = NumbersListAdapter()
        binding.recyclerView.adapter = adapter
        model.initList()
        observeData()
    }


    private fun observeData() {
        model.result().observe(this, { result ->
            when (result.first) {
                ResultType.SUCCESS -> showAlert(
                    getString(R.string.success),
                    String.format(getString(R.string.sum_elements), result.second)
                )
                ResultType.ERROR -> showAlert(getString(R.string.error), result.second)
            }
        })
        model.addItemEvent().observe(this, {
            adapter.insertItem()
        })
        model.deleteItemEvent().observe(this, {
            adapter.deleteItem()
        })
        model.calculateEvent().observe(this, {
            model.getSum(adapter.listNums)
        })
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("ok") { dialog, _ ->
                dialog.dismiss()
            }
        }
            .create()
            .show()
    }
}