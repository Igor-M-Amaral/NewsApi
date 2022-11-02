package com.example.igormattos.newsapi.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

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

        return binding.root
    }

    private fun setArgs(args: OverviewFragmentArgs) = binding.apply {
        Glide.with(requireActivity())
            .load(args.image)
            .placeholder(R.drawable.place_holder)
            .into(imageThumbnail)

        textTitle.text = args.title
        textDate.text = args.date
        textDescription.text = args.title
        textContent.text = args.content
        textAuthor.text = args.author

        buttonLink.setOnClickListener {
            val uri: Uri = Uri.parse(args.urlContent)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }
}