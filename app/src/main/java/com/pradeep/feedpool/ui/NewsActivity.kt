package com.pradeep.feedpool.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pradeep.feedpool.R
import com.pradeep.feedpool.databinding.ActivityNewsBinding
import com.pradeep.feedpool.db.ArticleDatabase
import com.pradeep.feedpool.repository.NewsRepository

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository= NewsRepository(ArticleDatabase.getArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        



        //bottom nav view with nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)



    }
}