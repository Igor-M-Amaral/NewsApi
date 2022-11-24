package com.example.igormattos.newsapi.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.databinding.FragmentSearchBinding
import com.example.igormattos.newsapi.utils.listener.NewsListener
import com.example.igormattos.newsapi.utils.methods.UtilsMethods
import com.example.igormattos.newsapi.view.viewmodel.ListViewModel
import com.example.igormattos.newsapi.view.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: ListViewModel by viewModels()
    private var adapterCategory = CategoryAdapter()

    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it) showProgressBar() else (hideProgressBar())

        })

        val listener = object : NewsListener {
            override fun onListClick(bundle: Article) {

                val action =
                    SearchFragmentDirections.actionSerachFragmentToOverviewFragment(
                        bundle.title ?: "unknown",
                        bundle.urlToImage ?: "unknown",
                        bundle.url ?: "unknown",
                        UtilsMethods.convertDate(bundle.publishedAt).toString() ?: "unknown",
                        bundle.content ?: "unknown",
                        bundle.author ?: "unknown"
                    )
                findNavController().navigate(action)
            }
        }

        initSearchBar()

        adapterCategory.attachListener(listener)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.searchFromAPI.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {

                binding.recyclerviewCategory.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.recyclerviewCategory.adapter = adapterCategory

                if (it != null) {
                    adapterCategory.submitList(it.articles)
                }
            }
        })
    }

    private fun initSearchBar() {

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchString = searchView.query.toString()
                viewModel.searchFromApi(searchString)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}