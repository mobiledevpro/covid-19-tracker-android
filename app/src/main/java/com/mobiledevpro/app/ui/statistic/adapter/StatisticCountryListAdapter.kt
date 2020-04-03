package com.mobiledevpro.app.ui.statistic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.ItemStatisticCountryBinding
import com.mobiledevpro.domain.model.DayStatistic

class StatisticCountryListAdapter : RecyclerView.Adapter<StatisticCountryListAdapter.ViewHolder>() {

    private val data: ArrayList<DayStatistic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticCountryItemViewHolder =
        StatisticCountryItemViewHolder(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is StatisticCountryItemViewHolder)
            holder.bind(data[holder.adapterPosition])
    }

    override fun getItemCount() = data.size

    fun populateList(update: ArrayList<DayStatistic>) {
        data.clear()
        data.addAll(update)
        notifyDataSetChanged()
    }

    //it uses to bind items via xml layout (see attribute app:items in <RecyclerView/>)
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<DayStatistic>?) {
            val adapter = adapter as StatisticCountryListAdapter

            adapter.populateList((if (items != null) ArrayList(items) else ArrayList()))
        }
    }

    abstract inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class StatisticCountryItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemStatisticCountryBinding = DataBindingUtil.inflate(
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