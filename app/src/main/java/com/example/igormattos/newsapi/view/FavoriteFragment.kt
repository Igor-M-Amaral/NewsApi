package com.example.igormattos.newsapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.databinding.FragmentFavoritesBinding
import com.example.igormattos.newsapi.databinding.FragmentSearchBinding
import com.example.igormattos.newsapi.utils.NewsListener
import com.example.igormattos.newsapi.utils.UtilsMethods
import com.example.igormattos.newsapi.view.adapter.CategoryAdapter
import com.example.igormattos.newsapi.view.adapter.FavoriteNewsAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: ListViewModel by viewModel()
    private var adapterCategory = FavoriteNewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        val listener = object : NewsListener {
            override fun onListClick(bundle: Article) {}

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
        }

        viewModel.listFavorites()

        adapterCategory.attachListener(listener)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.newsDB.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                binding.recyclerviewCategory.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.recyclerviewCategory.adapter = adapterCategory

                adapterCategory.submitList(it)

            }
        })
    }
}