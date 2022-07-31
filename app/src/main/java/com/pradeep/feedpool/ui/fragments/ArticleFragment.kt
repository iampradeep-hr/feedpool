package com.pradeep.feedpool.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pradeep.feedpool.R
import com.pradeep.feedpool.ui.NewsActivity
import com.pradeep.feedpool.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article ) {

    private lateinit var newsViewModel: NewsViewModel
    private val args:ArticleFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel=(activity as NewsActivity).newsViewModel
        val article=args.article
        webView.apply {
            webViewClient= WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        fab.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(view,"Article Saved Successfully",Snackbar.LENGTH_SHORT).show()
        }
    }


    


}