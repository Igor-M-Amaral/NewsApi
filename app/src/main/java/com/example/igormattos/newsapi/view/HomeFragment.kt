package com.example.igormattos.newsapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.databinding.HomeFragmentBinding
import com.example.igormattos.newsapi.view.adapter.CategoryAdapter
import com.example.igormattos.newsapi.view.adapter.TrendingPagerAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {

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

        binding.chip2.setOnClickListener(this)
        binding.chip3.setOnClickListener(this)
        binding.chip4.setOnClickListener(this)
        binding.chip5.setOnClickListener(this)
        binding.chip6.setOnClickListener(this)
        binding.chip7.setOnClickListener(this)


        observer()


        binding.homeViewPager.clipToPadding = false
        binding.homeViewPager.setPadding(0,0,250,0)
        binding.homeViewPager.pageMargin = 32

        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.chip2 ->{
                viewModel.load("sport")
                onResume()
            }
            R.id.chip3 -> {
                viewModel.load("entertainment")
            }
            R.id.chip4 -> {
                viewModel.load("health")
            }
            R.id.chip5 -> {
                viewModel.load("science")
            }
            R.id.chip6 -> {
                viewModel.load("business")
            }
            R.id.chip7 -> {
                viewModel.load("technology")
            }
        }
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

    private fun observer() {

        viewModel.trendingNews.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {

                if (it != null) {
                    binding.homeViewPager.adapter = TrendingPagerAdapter(requireActivity(), it.articles)
                }
            }
        })

    }

}