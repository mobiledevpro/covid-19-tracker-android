package com.mobiledevpro.app.ui.statistic

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentStatisticCountryBinding
import com.mobiledevpro.app.ui.statistic.adapter.StatisticCountryListAdapter
import com.mobiledevpro.app.ui.statistic.viewmodel.StatisticCountryViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_statistic_country.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Fragment for showing statistic by country
 */
class StatisticCountryFragment : BaseFragment() {

    private val args: StatisticCountryFragmentArgs by navArgs()

    private val statisticViewModel: StatisticCountryViewModel by sharedViewModel()

    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ViewGroup>

    private val subscriptions = CompositeDisposable()

    override fun getLayoutResId() = R.layout.fragment_statistic_country

    override fun getAppBarTitleString() = args.countryName

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

        val query = args.countryName
        statisticViewModel.observeConfirmedList(query)
        initBottomSheetView()
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        statisticViewModel.observeChartData()
            .subscribe { chartByDays.setDada(it) }
            .addTo(subscriptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
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

    private fun initBottomSheetView() {
        bottomSheetBehaviour = BottomSheetBehavior.from(layout_bottom_sheet)
    }
}