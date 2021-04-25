package com.shahzar.twitterclone.ui.main

import androidx.lifecycle.ViewModelProvider
import com.shahzar.twitterclone.NavMgr
import com.shahzar.twitterclone.R
import com.shahzar.twitterclone.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var navMgr: NavMgr

    private lateinit var viewModel: MainViewModel

    override fun getLayoutRes() = R.layout.activity_main

    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun setupObservers() {
        super.setupObservers()
    }

}