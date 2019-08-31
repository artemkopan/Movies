package com.movie.presentation.features.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import com.movie.common.SlideLayoutManager
import com.movie.presentation.R
import com.movie.presentation.base.AppFragment
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : AppFragment(R.layout.home_fragment) {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = MoviesPagerAdapter(resources)
        val snapHelper = PagerSnapHelper()
        home_list.adapter = adapter
        snapHelper.attachToRecyclerView(home_list)

        viewModel.mediaListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.mediaList = it.orEmpty()
        })
    }

}