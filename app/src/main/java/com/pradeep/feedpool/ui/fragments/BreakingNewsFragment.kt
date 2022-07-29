package com.pradeep.feedpool.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pradeep.feedpool.R
import com.pradeep.feedpool.ui.NewsActivity
import com.pradeep.feedpool.ui.NewsViewModel

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel=(activity as NewsActivity).newsViewModel
    }



}