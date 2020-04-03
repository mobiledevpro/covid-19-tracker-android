package com.mobiledevpro.app.utils.diff

import androidx.recyclerview.widget.DiffUtil
import com.mobiledevpro.domain.model.DayStatistic

class StatisticCountryDiffUtil(
    private val old: List<DayStatistic>,
    private val new: List<DayStatistic>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].date == new[newItemPosition].date

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]
}