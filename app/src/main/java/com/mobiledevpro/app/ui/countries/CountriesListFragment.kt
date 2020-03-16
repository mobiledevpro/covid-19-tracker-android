package com.mobiledevpro.app.ui.countries

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentCountriesListBinding
import com.mobiledevpro.app.ui.countries.adapter.CountriesListAdapter
import com.mobiledevpro.app.ui.mainscreen.viewmodel.TotalViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_countries_list.*
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun initPresenters() {
    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity())

        val dividerDrawable = requireActivity().getDrawable(R.drawable.list_item_divider);
        val divider = DividerItemDecoration(context, layoutManager.orientation);
        if (dividerDrawable != null)
            divider.setDrawable(dividerDrawable)

        rv_countries_list?.layoutManager = layoutManager
        rv_countries_list?.setHasFixedSize(true)
        rv_countries_list?.adapter = CountriesListAdapter()
        rv_countries_list.addItemDecoration(divider)
    }
}