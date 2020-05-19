package com.books.views.typebooks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.books.R
import com.books.adapters.BooksAdapter
import com.books.databinding.BooksTypeFragmentBinding
import com.books.interfaces.OnItemClickListener
import com.books.base.BaseFragment

class BooksTypeFragment : BaseFragment<BooksTypeFragmentBinding, BooksTypeViewModel>(),
    OnItemClickListener {
    private lateinit var navController: NavController
    override val fragmentLayoutId: Int
        get() = R.layout.books_type_fragment
    override val viewModelClass: Class<BooksTypeViewModel>
        get() = BooksTypeViewModel::class.java

    private lateinit var adapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        navController =
            Navigation.findNavController(activity as FragmentActivity, R.id.my_nav_host_fragment)

        viewModel.addDataToList()

        setAdapter()
    }

    private fun setAdapter() {
        adapter = BooksAdapter(viewModel.list)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        adapter.setItemSelectedListener(this)
    }

    override fun onClick(position: Int, type: String) {
        val bundle = Bundle().apply {
            putString("title", viewModel.list[position].name)
        }
        navController.navigate(R.id.action_booksTypeFragment_to_booksListFragment, bundle)
    }

    override fun onClick(data: Any) {

    }

}
