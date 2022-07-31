package com.pradeep.feedpool.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.feedpool.R
import com.pradeep.feedpool.adapters.NewsAdapter
import com.pradeep.feedpool.ui.NewsActivity
import com.pradeep.feedpool.ui.NewsViewModel
import com.pradeep.feedpool.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchNewsFragment: Fragment(R.layout.fragment_search_news) {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel=(activity as NewsActivity).newsViewModel
        setUpWithRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment,bundle)
        }

        var job: Job?=null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(1000)
                editable?.let {
                    if (editable.toString().trim().isNotEmpty()){
                        newsViewModel.getSearchNews(editable.trim().toString())
                    }
                }

            }
        }

        newsViewModel.searchNews.observe(viewLifecycleOwner, Observer{ response ->
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
        paginationProgressBar.visibility= View.INVISIBLE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility= View.VISIBLE
    }

    private fun setUpWithRecyclerView(){
        newsAdapter= NewsAdapter()
        rvSearchNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        }
    }
    


}