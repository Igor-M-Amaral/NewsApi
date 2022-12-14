package com.example.igormattos.newsapi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.utils.listener.NewsListener

class TrendingPagerAdapter(var context: Context, var articles: List<Article>, private val listener: NewsListener) :  PagerAdapter() {

    override fun getCount(): Int {
        return articles.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.item_pager_news, container, false)

        val image = view.findViewById<ImageView>(R.id.image_thumbnail)
        val title = view.findViewById<TextView>(R.id.text_title)

        Glide.with(context)
            .load(articles[position].urlToImage)
            .placeholder(R.drawable.place_holder)
            .into(image)

        title.text = articles[position].title


        view.setOnClickListener {
            listener.onListClick(articles[position])
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}