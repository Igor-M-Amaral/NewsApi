package com.example.igormattos.newsapi.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.databinding.FragmentOverviewBinding
import com.example.igormattos.newsapi.view.viewmodel.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: OverviewViewModel by viewModels()
    private val args: OverviewFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentOverviewBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setArgs(args)
        checkFavorite()

        binding.imageFavorite.setOnClickListener(this)



        return binding.root
    }

    override fun onClick(v: View) {
        viewModel.favoriteNews(args)
        viewModel.checkFavorite(args.title)
    }

    private fun setArgs(args: OverviewFragmentArgs) = binding.apply {
        Glide.with(requireActivity())
            .load(args.urlToImage)
            .placeholder(R.drawable.place_holder)
            .into(imageThumbnail)

        textTitle.text = args.title
        textDate.text = args.publishedAt
        textFullTitle.text = args.title
        textContent.text = args.content
        textAuthor.text = args.author

        buttonLink.setOnClickListener {
            val uri: Uri = Uri.parse(args.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun checkFavorite() {
        viewModel.favorite.observe(viewLifecycleOwner, Observer {
            binding.apply {
                if (it) {
                    imageFavorite.setImageResource(R.drawable.ic_favorite_red)
                } else {
                    imageFavorite.setImageResource(R.drawable.ic_favorite)
                }
            }
        })
        viewModel.checkFavorite(args.title)
    }

}