package com.mobiledevpro.app.utils.diff

import androidx.recyclerview.widget.DiffUtil
import com.mobiledevpro.domain.model.TotalCountry

class CountiesDiffUtil(
    private val old: List<TotalCountry>,
    private val new: List<TotalCountry>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].id == new[newItemPosition].id

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}