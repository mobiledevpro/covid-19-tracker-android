package com.mobiledevpro.app.ui.total

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentTotalBinding
import com.mobiledevpro.app.ui.main.viemodel.MainViewModel
import com.mobiledevpro.app.ui.total.viewmodel.TotalViewModel
import com.mobiledevpro.commons.activity.IBaseActivity
import com.mobiledevpro.commons.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * Main fragment for main activity
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro/
 *
 * #MobileDevPro
 */

class TotalFragment : BaseFragment() {

    private val totalViewModel: TotalViewModel by sharedViewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun getLayoutResId() = R.layout.fragment_total

    override fun getAppBarTitle() = R.string.app_name_main

    override fun getOptionsMenuResId() = R.menu.menu_total

    override fun getHomeAsUpIndicatorIcon() = 0

    override fun populateView(view: View, savedInstanceState: Bundle?): View {
        //databinding
        val binding = FragmentTotalBinding.bind(view)
            .apply {
                viewModel = totalViewModel
            }
        binding.lifecycleOwner = viewLifecycleOwner

        observeEvents()
        return binding.root
    }

    override fun initPresenters() {
        //add lifecycle observer to viewmodel
        lifecycle.addObserver(totalViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as IBaseActivity).setHomeAsUpIndicatorIcon(homeAsUpIndicatorIcon)
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.setFabActionShowCountries()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share_app -> mainViewModel.shareTheAppLink()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeEvents() {
        totalViewModel.eventShowError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { msg ->
                Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
            }
        })

    }
}
