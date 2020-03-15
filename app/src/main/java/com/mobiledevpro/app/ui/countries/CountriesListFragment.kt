package com.mobiledevpro.app.ui.countries

import android.os.Bundle
import android.view.View
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentCountriesListBinding
import com.mobiledevpro.app.ui.mainscreen.viewmodel.TotalViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Fragment for countries list
 *
 * Created by Dmitriy Chernysh on Mar 15, 2020.
 *
 * http://androiddev.pro
 *
 */
class CountriesListFragment : BaseFragment() {
    private val viewModel: TotalViewModel by sharedViewModel()

    override fun getLayoutResId() = R.layout.fragment_countries_list

    override fun getAppBarTitle() = R.string.app_name_countries_list

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_arrow_back_white_24dp

    override fun populateView(view: View, savedInstanceState: Bundle?): View {
        //databinding
        val binding = FragmentCountriesListBinding.bind(view)
                .apply {
                    totalViewModel = viewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner

        observeEvents(view)
        return binding.root
    }

    override fun initPresenters() {
    }

    private fun observeEvents(view: View) {
        //todo listen livedata changes here

    }
}