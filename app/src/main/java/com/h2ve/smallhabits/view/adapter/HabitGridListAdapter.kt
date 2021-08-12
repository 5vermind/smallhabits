package com.h2ve.smallhabits.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.FragmentBoardBinding
import com.h2ve.smallhabits.databinding.ListitemCardHabitsBinding
import com.h2ve.smallhabits.model.HabitTemp
import java.util.*

class HabitGridListAdapter : RecyclerView.Adapter<HabitGridListAdapter.HabitGridListViewHolder>() {

    private var list = arrayListOf<HabitTemp>()

    interface ItemClickListener {
        fun onClick(view: View, id: HabitTemp)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitGridListAdapter.HabitGridListViewHolder {
        val binding = ListitemCardHabitsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitGridListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitGridListAdapter.HabitGridListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class HabitGridListViewHolder(private val binding: ListitemCardHabitsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HabitTemp) {
//            Glide.with(itemView)
//                .load(item.thumbnail)
//                .apply(RequestOptions().placeholder(R.drawable.ic_plus_b))
//                .into(binding.ivBoardThumbnail)
//            listBinding.tvBoardTitle.text = item.title
//            listBinding.tvBoardCommentNum.text = item.commentCount.toString()
//            listBinding.root.setOnClickListener {
//                itemClickListener.onClick(it, item)
//            }
        }
    }
}