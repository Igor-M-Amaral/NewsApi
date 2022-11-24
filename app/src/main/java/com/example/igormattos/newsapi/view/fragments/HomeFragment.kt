package com.example.igormattos.newsapi.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.databinding.FragmentHomeBinding
import com.example.igormattos.newsapi.utils.listener.NewsListener
import com.example.igormattos.newsapi.utils.methods.UtilsMethods
import com.example.igormattos.newsapi.view.adapter.CategoryAdapter
import com.example.igormattos.newsapi.view.adapter.TrendingPagerAdapter
import com.example.igormattos.newsapi.view.viewmodel.ListViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ListViewModel by viewModels()
    private var adapterCategory = CategoryAdapter()
    private lateinit var shimmerViewPager: ShimmerFrameLayout
    private lateinit var shimmerViewCategory: ShimmerFrameLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = FragmentHomeBinding.inflate(layoutInflater)
        shimmerViewPager = binding.shimmerViewPager
        shimmerViewCategory = binding.shimmerViewContainer

        viewModel.load("sport")

        val listener = object : NewsListener {
            override fun onListClick(bundle: Article) {

                val action =
                    HomeFragmentDirections.actionHomeFragmentToOverviewFragment(
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

            chipSports.setOnCheckedChangeListener { _, _ ->
                viewModel.load("sport")
                textCategory.text = "Sport"
            }

            chipBusiness.setOnCheckedChangeListener { _, _ ->
                viewModel.load("business")
                textCategory.text = "Business"
            }

            chipEntertainement.setOnCheckedChangeListener { _, b ->
                viewModel.load("entertainment")
                textCategory.text = "Entertainment"
            }
            chipHealth.setOnCheckedChangeListener { _, _ ->
                viewModel.load("health")
                textCategory.text = "Health"
            }

            chipTechnology.setOnCheckedChangeListener { _, _ ->
                viewModel.load("technology")
                textCategory.text = "Technology"
            }

            chipScience.setOnCheckedChangeListener { _, _ ->
                viewModel.load("science")
                textCategory.text = "Science"
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

                    shimmerViewCategory.stopShimmer()
                    shimmerViewCategory.visibility = View.GONE
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

                    shimmerViewPager.stopShimmer()
                    shimmerViewPager.visibility = View.GONE
                }
            }
        })
    }

}