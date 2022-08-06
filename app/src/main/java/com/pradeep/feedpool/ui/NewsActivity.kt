package com.pradeep.feedpool.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pradeep.feedpool.NewsApplication
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
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository,
            application
        )
        newsViewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        



        //bottom nav view with nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)



    }

    fun showNetworkDialog(message:String){
        val dialog = BottomSheetDialog(this)
        val bottomSheet = layoutInflater.inflate(R.layout.my_dialog, null)
        val bs_text=bottomSheet.findViewById<TextView>(R.id.show_message)
        bs_text.text=message
        bottomSheet.findViewById<Button>(R.id.btn_close).setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.setCancelable(false)
        dialog.setContentView(bottomSheet)
        dialog.dismissWithAnimation
        dialog.show()

    }
}