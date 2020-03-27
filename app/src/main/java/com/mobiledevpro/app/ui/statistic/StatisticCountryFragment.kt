package com.mobiledevpro.app.ui.statistic

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentStatisticCountryBinding
import com.mobiledevpro.app.ui.statistic.adapter.StatisticCountryListAdapter
import com.mobiledevpro.app.ui.statistic.viewmodel.StatisticCountryViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_statistic_country.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Fragment for showing statistic by country
 */
class StatisticCountryFragment : BaseFragment() {

    private val statisticViewModel: StatisticCountryViewModel by sharedViewModel()

    override fun getLayoutResId() = R.layout.fragment_statistic_country

    //TODO: add country name to Toolbar
//    override fun getAppBarTitle() = query

    override fun getHomeAsUpIndicatorIcon(): Int = R.drawable.ic_arrow_back_white_24dp

    override fun initPresenters() {
        lifecycle.addObserver(statisticViewModel)
    }

    override fun populateView(layoutView: View, savedInstanceState: Bundle?): View {
        val binding = FragmentStatisticCountryBinding.bind(layoutView)
            .apply {
                viewModel = statisticViewModel
            }
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())

        val dividerDrawable = requireContext().getDrawable(R.drawable.list_item_divider)
        val divider = DividerItemDecoration(context, layoutManager.orientation)
        dividerDrawable?.let { divider.setDrawable(it) }

        rvStatistic?.layoutManager = layoutManager
        rvStatistic?.setHasFixedSize(true)
        rvStatistic?.adapter = StatisticCountryListAdapter()
        rvStatistic.addItemDecoration(divider)
    }
}