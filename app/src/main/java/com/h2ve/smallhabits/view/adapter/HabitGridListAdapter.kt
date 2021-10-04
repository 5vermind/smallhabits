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

    private var list = arrayListOf<HabitTemp>(HabitTemp("title_1", 90, "", 1), HabitTemp("title_2", 75, "", 2),
        HabitTemp("title_3", 15, "", 3), HabitTemp("title_4", 45, "", 4), HabitTemp("title_5", 33, "", 5))

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
            binding.tvTitle.text = item.title
            binding.tvProgress.text = item.progress.toString()
            binding.root.setOnClickListener {
                itemClickListener.onClick(it, item)
            }
        }
    }
}