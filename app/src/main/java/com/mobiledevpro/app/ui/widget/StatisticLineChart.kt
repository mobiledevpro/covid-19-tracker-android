package com.mobiledevpro.app.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ui.statistic.viewmodel.StatisticCountryViewModel
import com.mobiledevpro.app.utils.toNumberWithAbbreviation
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class StatisticLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val lineChart = LineChart(context)

    init {

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        lineChart.layoutParams = params
        this.addView(lineChart)

        setupChart()
    }

    private fun setupChart() {
        lineChart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            legend.isEnabled = false

            lineChart.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = ContextCompat.getColor(context, android.R.color.white)
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val millis = TimeUnit.MILLISECONDS.toMillis(value.toLong())
                        return DATE_FORMAT.format(Date(millis))
                    }
                }
            }

            lineChart.axisLeft.apply {
                axisMinimum = 0f
                setDrawAxisLine(false)
                textColor = ContextCompat.getColor(context, android.R.color.white)
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String =
                        value.toNumberWithAbbreviation()
                }
            }

            lineChart.axisRight.apply {
                isEnabled = false
            }
        }

        lineChart.invalidate()
    }

    fun setDada(chartLinesView: StatisticCountryViewModel.ChartLinesView) {
        lineChart.highlightValue(null)
        lineChart.clear()

        val confirmedLine: ILineDataSet = createConfirmedChartLine(chartLinesView.confirmed)
        val deathsLine: ILineDataSet = createDeathsChartLine(chartLinesView.death)
        val recoveredLine: ILineDataSet = createRecoveredChartLine(chartLinesView.recovered)

        val sets = arrayListOf(
            confirmedLine,
            deathsLine,
            recoveredLine
        )

        if (sets.isNotEmpty()) {
            val linesData = LineData(sets)
            lineChart.data = linesData
        }

        lineChart.invalidate()
    }

    private fun createConfirmedChartLine(data: ArrayList<Entry>): LineDataSet {
        val lineDataSet = LineDataSet(data, "")
        lineDataSet.apply {
            lineWidth = 2f
            setDrawCircles(false)
            setDrawCircleHole(false)
            setDrawValues(false)
            fillAlpha = 65
            color = ContextCompat.getColor(context, R.color.colorTextPrimaryRed)
            highLightColor = ContextCompat.getColor(context, android.R.color.white)
        }
        return lineDataSet
    }

    private fun createDeathsChartLine(data: ArrayList<Entry>): LineDataSet {
        val lineDataSet = LineDataSet(data, "")
        lineDataSet.apply {
            lineWidth = 2f
            setDrawCircles(false)
            setDrawCircleHole(false)
            setDrawValues(false)
            fillAlpha = 65
            color = ContextCompat.getColor(context, R.color.colorTextSecondary)
            highLightColor = ContextCompat.getColor(context, android.R.color.white)
        }
        return lineDataSet
    }

    private fun createRecoveredChartLine(data: ArrayList<Entry>): LineDataSet {
        val lineDataSet = LineDataSet(data, "")
        lineDataSet.apply {
            lineWidth = 2f
            setDrawCircles(false)
            setDrawCircleHole(false)
            setDrawValues(false)
            fillAlpha = 65
            color = ContextCompat.getColor(context, R.color.colorTextPrimaryGreen)
            highLightColor = ContextCompat.getColor(context, android.R.color.white)
        }
        return lineDataSet
    }


    private companion object {
        val DATE_FORMAT = SimpleDateFormat("MMM dd", Locale.getDefault())
    }
}
