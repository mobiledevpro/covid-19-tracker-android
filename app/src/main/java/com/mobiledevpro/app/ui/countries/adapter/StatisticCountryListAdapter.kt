package com.mobiledevpro.app.ui.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.ItemStatisticBinding
import com.mobiledevpro.app.utils.diff.StatisticCountryDiffUtil
import com.mobiledevpro.domain.model.DayStatistic

class StatisticCountryListAdapter : RecyclerView.Adapter<StatisticCountryListAdapter.ViewHolder>() {

    private val data: ArrayList<DayStatistic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticItemViewHolder =
        StatisticItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is StatisticItemViewHolder -> holder.bind(data[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = data.size

    fun populateList(update: ArrayList<DayStatistic>) {
        val callback = StatisticCountryDiffUtil(data, update)
        val result = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(update)
        result.dispatchUpdatesTo(this)
    }

    //it uses to bind items via xml layout (see attribute app:items in <RecyclerView/>)
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<DayStatistic>) {
            val adapter = adapter as StatisticCountryListAdapter
            adapter.populateList(ArrayList(items))
        }
    }

    abstract inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class StatisticItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemStatisticBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_statistic_country,
            parent,
            false
        )
    ) : ViewHolder(binding.root) {

        fun bind(item: DayStatistic) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}