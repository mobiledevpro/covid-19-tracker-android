package com.mobiledevpro.app.ui.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Observer
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ui.main.viemodel.MainViewModel
import com.mobiledevpro.app.utils.FabActionNavigation
import com.mobiledevpro.app.utils.Navigation
import com.mobiledevpro.app.utils.show
import com.mobiledevpro.app.utils.showCountiesList
import com.mobiledevpro.commons.activity.BaseActivity
import com.mobiledevpro.commons.helpers.BaseResourcesHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutResId() = R.layout.activity_main

    override fun isAdjustFontScaleToNormal() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        //set navigation bar translucent
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onCreate(savedInstanceState)

        applyWindowInsets(findViewById(android.R.id.content))
    }

    override fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        toolbar?.let { setSupportActionBar(it) }
    }

    override fun initPresenters() {
        //no need
    }

    override fun populateView(layoutView: View) {
        //work with view here

        observeEvents()
    }

    private fun observeEvents() {
        mainViewModel.eventNavigateTo.observe(this, Observer {
            it.getContentIfNotHandled()?.let { navigateTo ->
                when (navigateTo) {
                    Navigation.NAVIGATE_TO_COUNTRIES_LIST -> showCountiesList(R.id.fragment_nav_host)
                }
            }
        })

        mainViewModel.eventFabAction.observe(this, Observer {
            it.getContentIfNotHandled()?.let { action ->
                when (action) {
                    FabActionNavigation.ACTION_SHOW_COUNTRIES ->
                        fab_main_action?.apply {
                            this.setOnClickListener { mainViewModel.showCountriesList() }
                            this.setImageDrawable(
                                BaseResourcesHelper.getDrawableCompatible(this@MainActivity, R.drawable.ic_world_24dp))
                            this.show(true)
                        }

                    FabActionNavigation.ACTION_SHOW_COUNTRY_SEARCH_BAR ->
                        fab_main_action?.apply {
                            // this.hide() - it does not work properly
                            this.setOnClickListener { mainViewModel.showSearchCountryBar() }
                            this.setImageDrawable(
                                BaseResourcesHelper.getDrawableCompatible(this@MainActivity, R.drawable.ic_search_24))
                            this.show(true)
                        }

                    FabActionNavigation.ACTION_HIDE ->
                        fab_main_action?.apply {
                            this.show(false)
                        }

                }
            }
        })

    }

    private fun applyWindowInsets(view: View) {
        //Use Window Insets to set top and bottom padding's to our activity
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            v.updatePadding(
                left = insets.systemWindowInsetLeft,
                top = insets.systemWindowInsetTop,
                right = insets.systemWindowInsetRight,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }
}
