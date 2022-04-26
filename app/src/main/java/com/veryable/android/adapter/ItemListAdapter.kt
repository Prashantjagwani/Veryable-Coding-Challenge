package com.veryable.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.R
import com.veryable.android.data.ApiResponseItem
import com.veryable.android.databinding.SingleListItemBinding
import com.veryable.android.listener.ItemClickListener
import com.veryable.android.utils.Constants.BANK_TYPE
import com.veryable.android.utils.Constants.CARD_TYPE

class ItemListAdapter : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private var list: List<ApiResponseItem>? = null
    private var itemClickListener: ItemClickListener? = null

    fun setList(list: List<ApiResponseItem>?) {
        this.list = list
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            SingleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(
                list?.get(holder.adapterPosition),
                holder.adapterPosition
            )
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ItemViewHolder(private val binding: SingleListItemBinding) : RecyclerView.ViewHolder
        (binding.root) {

        fun bind(apiResponseItem: ApiResponseItem?) {
            binding.nameTxt.text = apiResponseItem?.accountName ?: ""
            binding.descTxt.text = apiResponseItem?.desc ?: ""
            when (apiResponseItem?.accountType ?: "") {
                CARD_TYPE -> {
                    binding.itemIcon.setImageResource(R.drawable.card_icon_black)
                    binding.typeTxt.text = binding.root.resources.getString(R.string.card_instant)
                }
                BANK_TYPE -> {
                    binding.itemIcon.setImageResource(R.drawable.bank_icon_black)
                    binding.typeTxt.text =
                        binding.root.resources.getString(R.string.bank_account_ach_same_day)
                }
            }
        }
    }

}