package com.pradeep.feedpool.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.feedpool.R
import com.pradeep.feedpool.adapters.NewsAdapter
import com.pradeep.feedpool.ui.NewsActivity
import com.pradeep.feedpool.ui.NewsViewModel
import com.pradeep.feedpool.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpWithRecyclerView()

        newsViewModel=(activity as NewsActivity).newsViewModel
        newsViewModel.breakingNews.observe(viewLifecycleOwner,Observer{ response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                     response.data?.let {
                         newsAdapter.differ.submitList(it.articles)
                     }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, "Something Went Wrong :/", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility=View.INVISIBLE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility=View.VISIBLE
    }

    private fun setUpWithRecyclerView(){
        newsAdapter= NewsAdapter()
        rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        }
    }



}