package com.mobiledevpro.app.ui.countries

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentCountriesListBinding
import com.mobiledevpro.app.ui.countries.adapter.CountriesListAdapter
import com.mobiledevpro.app.ui.main.viemodel.MainViewModel
import com.mobiledevpro.app.ui.total.viewmodel.TotalViewModel
import com.mobiledevpro.app.utils.Navigation
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
    private val totalViewModel: TotalViewModel by sharedViewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private lateinit var searchView: SearchView

    override fun getLayoutResId() = R.layout.fragment_countries_list

    override fun getAppBarTitle() = R.string.app_name_countries_list

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_arrow_back_white_24dp

    override fun populateView(view: View, savedInstanceState: Bundle?): View {
        //databinding
        val binding = FragmentCountriesListBinding.bind(view)
            .apply {
                viewModel = totalViewModel
            }
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeEvents()
    }

    override fun initPresenters() {
    }

    override fun getOptionsMenuResId(): Int = R.menu.menu_search

    override fun onPrepareOptionsMenu(menu: Menu) {
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        initSearchView()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.setFabActionShowCountrySearch()
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

    private fun initSearchView() {
        searchView.apply {

            queryHint = getString(R.string.search_country_hint)

            totalViewModel.getQuery().apply {
                if (this.isNotEmpty()) {
                    onActionViewExpanded()
                    setQuery(this, true)
                }
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = true

                override fun onQueryTextChange(query: String?): Boolean {
                    totalViewModel.getCountiesByQuery(query ?: "")
                    return true
                }
            })
        }
    }

    private fun observeEvents() {
        mainViewModel.eventNavigateTo.observe(viewLifecycleOwner, Observer {
            it.peekContent().let { navigateTo ->
                if (navigateTo == Navigation.NAVIGATE_TO_SEARCH_COUNTRY) {
                    searchView.apply {
                        onActionViewExpanded()
                        setQuery("", false)
                    }
                }
            }
        })

    }
}