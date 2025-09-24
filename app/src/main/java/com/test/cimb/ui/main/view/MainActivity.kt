package com.test.cimb.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cimb.adapter.UserAdapter
import com.test.cimb.databinding.ActivityMainBinding
import com.test.cimb.ui.main.presenter.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
        viewModel.getUsers(this)
    }

    private fun initView() {
        binding.apply {
            toolbar.title = "User List"
            rvUsers.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = userAdapter
            }
        }
    }

    private fun initObserver() {
        viewModel.apply {
            isLoading.observe(this@MainActivity) {
                binding.pbLoading.isVisible = it
            }
            userList.observe(this@MainActivity) {
                userAdapter.submitList(it)
            }
            error.observe(this@MainActivity) {
                if (it.isNotEmpty()) {
                    binding.tvErrorMessage.isVisible = true
                    binding.rvUsers.isVisible = false
                    binding.tvErrorMessage.text = it
                } else {
                    binding.tvErrorMessage.isVisible = false
                    binding.rvUsers.isVisible = true
                }
            }
        }
    }
}