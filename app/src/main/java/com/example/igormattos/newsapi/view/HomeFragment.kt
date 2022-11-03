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
import com.example.igormattos.newsapi.databinding.HomeFragmentBinding
import com.example.igormattos.newsapi.utils.NewsListener
import com.example.igormattos.newsapi.utils.UtilsMethods
import com.example.igormattos.newsapi.view.adapter.CategoryAdapter
import com.example.igormattos.newsapi.view.adapter.TrendingPagerAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: ListViewModel by viewModel()
    private var adapterCategory = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(layoutInflater)

        viewModel.load("sport")


        val listener = object : NewsListener {
            override fun onListClick(bundle: Article) {

                val action = HomeFragmentDirections.actionHomeFragmentToOverviewFragment(
                    bundle.description ?: "unknown",
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

        adapterCategory.attachListener(listener)

        binding.apply {

            chipSports.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.load("sport")
            }

            chipBusiness.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.load("business")
            }

            chipEntertainement.setOnCheckedChangeListener { _, b ->
                viewModel.load("entertainment")
            }
            chipHealth.setOnCheckedChangeListener { _, _ ->
                viewModel.load("health")
            }

            chipTechnology.setOnCheckedChangeListener { _, _ ->
                viewModel.load("technology")
            }

            chipScience.setOnCheckedChangeListener { _, _ ->
                viewModel.load("science")
            }

            homeViewPager.clipToPadding = false
            homeViewPager.setPadding(0, 0, 250, 0)
            homeViewPager.pageMargin = 32

        }

        observer(listener)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.newsByCategory.observe(viewLifecycleOwner, Observer {
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



    private fun observer(listener: NewsListener) {
        viewModel.trendingNews.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {

                if (it != null) {
                    binding.homeViewPager.adapter =
                        TrendingPagerAdapter(requireActivity(), it.articles, listener)
                }
            }
        })
    }

}