package com.veryable.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.veryable.android.R
import com.veryable.android.data.ApiResponseItem
import com.veryable.android.databinding.ActivityPayoutsDetailBinding
import com.veryable.android.utils.Constants.BANK_TYPE
import com.veryable.android.utils.Constants.CARD_TYPE
import com.veryable.android.utils.Constants.ITEM_DATA
import com.veryable.android.viewmodel.PayoutsDetailActivityViewModel

// detail screen for the list
class PayoutsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayoutsDetailBinding
    private var apiResponseItem: ApiResponseItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_detail)

        initView()
        initViewModel()
        getIntentData()
    }

    // initialize the views and custom toolbar
    private fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    // finish activity on back arrow clicked
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    // fetch data from the intent
    fun getIntentData() {
        apiResponseItem = intent.getParcelableExtra(ITEM_DATA)
    }

    // initialize viewmodel and attach observer to the item data
    fun initViewModel() {
        val viewModel =
            ViewModelProvider(this@PayoutsDetailActivity).get(PayoutsDetailActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this@PayoutsDetailActivity) {
            when (apiResponseItem?.accountType) {
                BANK_TYPE -> {
                    binding.icon.setImageResource(R.drawable.bank_icon_black_big)
                }
                CARD_TYPE -> {
                    binding.icon.setImageResource(R.drawable.card_icon_black_big)
                }
            }
            binding.nameTxt.text = apiResponseItem?.accountName ?: ""
            binding.descTxt.text = apiResponseItem?.desc ?: ""
        }
        viewModel.setItemData(apiResponseItem)
    }
}