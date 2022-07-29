package com.pradeep.feedpool.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pradeep.feedpool.R
import com.pradeep.feedpool.ui.NewsActivity
import com.pradeep.feedpool.ui.NewsViewModel

class ArticleFragment: Fragment(R.layout.fragment_article ) {

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel=(activity as NewsActivity).newsViewModel
    }
    


}