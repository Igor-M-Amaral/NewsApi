package com.example.igormattos.newsapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.databinding.FragmentFavoritesBinding
import com.example.igormattos.newsapi.utils.listener.FavoritesListener
import com.example.igormattos.newsapi.utils.methods.UtilsMethods
import com.example.igormattos.newsapi.view.adapter.FavoriteNewsAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: ListViewModel by viewModel()
    private val adapterCategory = FavoriteNewsAdapter()

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val listener = object : FavoritesListener {

            override fun onListClickFavorites(bundle: NewsDB) {

                val action = FavoriteFragmentDirections.actionNavFavoritesToOverviewFragment(
                    bundle.title ?: "unknown",
                    bundle.urlToImage ?: "unknown",
                    bundle.url ?: "unknown",
                    UtilsMethods.convertDate(bundle.publishedAt).toString() ?: "unknown",
                    bundle.content ?: "unknown",
                    bundle.author ?: "unknown"
                )
                findNavController().navigate(action)
            }

            override fun onDeleteByTitle(newsDB: NewsDB) {
                viewModel.deleteFavorite(newsDB)
                onResume()
            }

        }
        initSearchBar()

        adapterCategory.attachListener(listener)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFavorites()

        viewModel.newsDB.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                binding.recyclerviewCategory.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.recyclerviewCategory.adapter = adapterCategory

                adapterCategory.submitList(it)

            }
        })
    }

    private fun initSearchBar() {
        with(binding.toolbar) {
            this.inflateMenu(R.menu.search_menu)

            val searchItem = menu.findItem(R.id.menu_search)

            searchView = searchItem.actionView as SearchView


            searchView.isIconified = false

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchString = searchView.query.toString()
                    viewModel.searchFromDB(searchString)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {

                        viewModel.searchFromDB(it)
                    }
                    return true
                }
            })
        }

    }
}