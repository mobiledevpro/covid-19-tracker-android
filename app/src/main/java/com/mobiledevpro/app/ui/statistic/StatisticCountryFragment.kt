package com.mobiledevpro.app.ui.statistic

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

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
        initCharts()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        statisticViewModel.observeChartData()
            .subscribe(::renderCharts)
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

    private fun initCharts() {
            chartByDays?.apply {
                // no description text
                description.isEnabled = false
                // enable touch gestures
                setTouchEnabled(true)
                dragDecelerationFrictionCoef = 0.9f
                // enable scaling and dragging
                isDragEnabled = true
                setScaleEnabled(true)
                setDrawGridBackground(false)
                isHighlightPerDragEnabled = true
                // set an alternative background color
                setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))

            chartByDays?.also { chart ->
                chart.axisRight.isEnabled = false
                chart.legend.isEnabled = false
                chart.setTouchEnabled(false)
                chart.xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    typeface = Typeface.DEFAULT
                    textSize = 11f
                    yOffset = 5f
                    setLabelCount(5, false)
                    textColor = ContextCompat.getColor(context, android.R.color.white)
                    valueFormatter = object : IAxisValueFormatter {
                        private val format = SimpleDateFormat("d MMM", Locale.ENGLISH)
                        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                            if (value == 0f) {
                                setLabelCount(0, true)
                                return "0"
                            }
                            return format.format(Date(TimeUnit.MILLISECONDS.toMillis(value.toLong())))
                        }
                    }
                }
                chart.axisLeft.apply {
                    setLabelCount(5, false)
                    axisMinimum = 0f
                    textSize = 11f
                    xOffset = 5f
                    textColor = ContextCompat.getColor(context, android.R.color.white)
                    setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                    typeface = Typeface.DEFAULT
                }

            }

            chartByDays?.legend?.isEnabled = false
        }
    }

    private fun initBottomSheetView() {
        bottomSheetBehaviour = BottomSheetBehavior.from(layout_bottom_sheet)
    }

    private fun renderCharts(entries: ArrayList<Entry>) {
        val lineDataSet = LineDataSet(entries, "DataSet")
        lineDataSet.apply {
            axisDependency = YAxis.AxisDependency.LEFT

            color = ContextCompat.getColor(context!!, R.color.colorTextPrimaryRed)
            valueTextColor = ContextCompat.getColor(context!!, android.R.color.white)
            lineWidth = 2f
            setDrawCircles(false)
            setDrawValues(false)
            fillAlpha = 65
            fillColor = ContextCompat.getColor(context!!, R.color.colorAccent)
            highLightColor = ContextCompat.getColor(context!!, android.R.color.white)
            setDrawCircleHole(false)

            // create a data object with the data sets
            val data = LineData(lineDataSet)
            data.setValueTextColor(Color.WHITE)
            data.setValueTextSize(10f)
            // set Data
            chartByDays?.data = data

        }
        chartByDays.invalidate()
    }
}
