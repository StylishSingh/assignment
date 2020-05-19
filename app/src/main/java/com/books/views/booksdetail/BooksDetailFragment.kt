package com.books.views.booksdetail

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.books.R
import com.books.databinding.BooksDetailFragmentBinding
import com.books.base.BaseFragment


class BooksDetailFragment : BaseFragment<BooksDetailFragmentBinding, BooksDetailViewModel>() {
    override val fragmentLayoutId: Int
        get() = R.layout.books_detail_fragment
    override val viewModelClass: Class<BooksDetailViewModel>
        get() = BooksDetailViewModel::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url=arguments?.getString("url")

        binding.viewmodel=viewModel

        binding.webView.loadUrl(url)

        // Enable Javascript
        // Enable Javascript
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        // Force links and redirects to open in the WebView instead of in a browser
        binding.webView.webViewClient = WebViewClient()


    }


}
