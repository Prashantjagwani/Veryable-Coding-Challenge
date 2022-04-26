package com.veryable.android.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.veryable.android.R
import com.veryable.android.adapter.ItemListAdapter
import com.veryable.android.data.ApiResponseItem
import com.veryable.android.databinding.ActivityPayoutsListBinding
import com.veryable.android.listener.ItemClickListener
import com.veryable.android.utils.Constants.BANK_TYPE
import com.veryable.android.utils.Constants.CARD_TYPE
import com.veryable.android.utils.Constants.ITEM_DATA
import com.veryable.android.utils.DividerItemDecorator
import com.veryable.android.viewmodel.PayoutsListActivityViewModel

// list screen with api data shown in recyclerview
class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayoutsListBinding
    private lateinit var bankAdapter: ItemListAdapter
    private lateinit var cardAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        initView()
        initViewModel()
    }

    // initialize the views and custom toolbar
    private fun initView() {
        setSupportActionBar(binding.toolbar)

        // initialize bank recyclerview and attach the adapter
        binding.bankRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PayoutsListActivity)
            bankAdapter = ItemListAdapter()
            adapter = bankAdapter
            val itemDecoration = ContextCompat.getDrawable(
                this.context, R.drawable
                    .item_list_divider
            )?.let { it }?.let {
                DividerItemDecorator(
                    it
                )
            }
            itemDecoration?.let { addItemDecoration(it) }
        }

        // initialize card recyclerview and attach the adapter
        binding.cardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PayoutsListActivity)
            cardAdapter = ItemListAdapter()
            adapter = cardAdapter
            val itemDecoration = ContextCompat.getDrawable(
                this.context, R.drawable
                    .item_list_divider
            )?.let { it }?.let {
                DividerItemDecorator(
                    it
                )
            }
            itemDecoration?.let { addItemDecoration(it) }
        }
    }

    // initialize viewmodel for the list screen to observe the api data and notify to the adapter
    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel =
            ViewModelProvider(this@PayoutsListActivity).get(PayoutsListActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this@PayoutsListActivity) {
            it?.let {
                cardAdapter.setList(it.filter { it1 -> it1.accountType == CARD_TYPE })
                cardAdapter.notifyDataSetChanged()
                bankAdapter.setList(it.filter { it1 -> it1.accountType == BANK_TYPE })
                bankAdapter.notifyDataSetChanged()

            } ?: kotlin.run {
                Toast.makeText(this@PayoutsListActivity, "Error getting list", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // set on click listener on the item click for card list
        cardAdapter.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(apiResponseItem: ApiResponseItem?, position: Int) {
                viewModel.onItemClick(apiResponseItem, position)
            }

        })

        // set on click listener on the item click for bank list
        bankAdapter.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(apiResponseItem: ApiResponseItem?, position: Int) {
                viewModel.onItemClick(apiResponseItem, position)
            }

        })

        // observe the item data on click
        viewModel.getLiveItemObserver().observe(this@PayoutsListActivity) {
            Intent(this@PayoutsListActivity, PayoutsDetailActivity::class.java).apply {
                putExtra(ITEM_DATA, it)
                startActivity(this)
            }
        }
        viewModel.getApiList()
    }
}