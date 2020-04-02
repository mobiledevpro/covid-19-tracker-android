package com.mobiledevpro.app.ui.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.ItemCountryBinding
import com.mobiledevpro.app.utils.diff.CountiesDiffUtil
import com.mobiledevpro.domain.model.TotalCountry

/**
 * Adapter for RecyclerView Countries list
 *
 * Created by Dmitriy Chernysh on Mar 16, 2020.
 *
 * http://androiddev.pro
 *
 */
class CountriesListAdapter(
    private val onClick: (countryName: String) -> Unit
) : RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {

    private var countriesList: ArrayList<TotalCountry> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder =
        CountryItemViewHolder(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is CountryItemViewHolder)
            holder.bind(countriesList[holder.adapterPosition], onClick)
    }

    override fun getItemCount() = countriesList.size

    fun populateList(update: ArrayList<TotalCountry>) {
        val callback = CountiesDiffUtil(countriesList, update)
        val result = DiffUtil.calculateDiff(callback)
        countriesList.clear()
        countriesList.addAll(update)
        result.dispatchUpdatesTo(this)
    }

    //it uses to bind items via xml layout (see attribute app:items in <RecyclerView/>)
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<TotalCountry>?) {
            val adapter = adapter as CountriesListAdapter
            adapter.populateList(if (items == null) ArrayList() else ArrayList(items))
        }
    }

    abstract inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class CountryItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemCountryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )

    ) : ViewHolder(binding.root) {

        fun bind(
            item: TotalCountry,
            onClick: (countryName: String) -> Unit
        ) {
            binding.item = item
            binding.executePendingBindings()

            itemView.setOnClickListener { onClick(item.country) }
        }
    }
}