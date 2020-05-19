package com.books.views.booklist

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.books.R
import com.books.adapters.BooksListAdapter
import com.books.databinding.BooksListFragmentBinding
import com.books.interfaces.OnItemClickListener
import com.books.models.Result
import com.books.utils.GridItemDecoration
import com.books.views.MainActivity
import com.books.base.BaseFragment


class BooksListFragment : BaseFragment<BooksListFragmentBinding, BooksListViewModel>(),
    OnItemClickListener, TextWatcher, View.OnTouchListener {
    private lateinit var navController: NavController
    override val fragmentLayoutId: Int
        get() = R.layout.books_list_fragment
    override val viewModelClass: Class<BooksListViewModel>
        get() = BooksListViewModel::class.java

    private lateinit var adapter: BooksListAdapter


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        navController =
            Navigation.findNavController(activity as FragmentActivity, R.id.my_nav_host_fragment)


        val title=arguments?.getString("title")

        (activity as MainActivity).supportActionBar?.title = title

        setObservers()

        setAdapter()

        binding.tieSearch.addTextChangedListener(this)
        binding.tieSearch.setOnTouchListener(this)
    }

    private fun setObservers() {


        viewModel.getBooks("", false).observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setAdapter() {
        adapter = BooksListAdapter()

        binding.recyclerview.layoutManager = GridLayoutManager(context, 3)


        binding.recyclerview.addItemDecoration(GridItemDecoration(12, 12))

        binding.recyclerview.adapter = adapter
        adapter.setItemSelectedListener(this)
    }

    override fun onClick(position: Int, type: String) {

    }

    override fun onClick(data: Any) {
        println("BooksListFragment.onClick")

        val resultData = data as Result

        var url = ""

        if (resultData.formats.texthtmlCharsetutf8 != null) {
            url = resultData.formats.texthtmlCharsetutf8
            if (url.contains(".zip")) {
                val subUrl = resultData.formats.texthtmlCharsetutf8.substringAfterLast("/")
                val suffixUrl = "/$subUrl.htm"
                url.replace(".zip", suffixUrl)
            }

            nextDetailFragment(url)
        } else if (resultData.formats.applicationpdf != null) {
            url = resultData.formats.applicationpdf
            if (url.contains(".zip")) {
                val subUrl = resultData.formats.applicationpdf.substringAfterLast("/")
                val suffixUrl = "/$subUrl.htm"
                url.replace(".zip", suffixUrl)
            }
            nextDetailFragment(url)
        } else if (resultData.formats.textplainCharsetutf8 != null) {
            url = resultData.formats.textplainCharsetutf8
            if (url.contains(".zip")) {
                val subUrl = resultData.formats.textplainCharsetutf8.substringAfterLast("/")
                val suffixUrl = "/$subUrl.htm"
                url.replace(".zip", suffixUrl)
            }
            nextDetailFragment(url)
        }else if (resultData.formats.applicationzip != null) {
            url = resultData.formats.applicationzip
            if (url.contains(".zip")) {
                val subUrl = resultData.formats.applicationzip.substringAfterLast("/")
                val suffixUrl = "/$subUrl.htm"
                url.replace(".zip", suffixUrl)
            }
            nextDetailFragment(url)
        }else{
            basicAlert()
        }



    }

    private fun nextDetailFragment(url:String){
        val bundle = Bundle().apply {
            putString("url", url)
        }

        navController.navigate(R.id.action_booksListFragment_to_booksDetailFragment, bundle)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString().isNotEmpty()) {
            binding.tieSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cancel, 0)
        } else {
            binding.tieSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0)
        }
        if (s.toString().length > 2) {

            viewModel.getBooks(s.toString(), true)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val drawableRight = 2;

        if (event?.action == MotionEvent.ACTION_UP && binding.tieSearch.compoundDrawables[drawableRight] != null) {
            if (event.rawX >= (binding.tieSearch.right - binding.tieSearch.compoundDrawables[drawableRight].bounds.width())) {
                // your action here
                binding.tieSearch.setText("")
                binding.tieSearch.clearFocus()
                hideKeyboard(binding.tieSearch)
                return true;
            }
        }
        return false;
    }

    fun basicAlert(){

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setMessage("No viewable version available")
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }


    }
    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
       dialog.dismiss()
    }
}
