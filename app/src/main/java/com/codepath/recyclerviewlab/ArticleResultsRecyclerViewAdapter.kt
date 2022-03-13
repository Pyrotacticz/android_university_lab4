package com.codepath.recyclerviewlab

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.recyclerviewlab.models.Article
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticleResultsRecyclerViewAdapter :
    RecyclerView.Adapter<ArticleResultsRecyclerViewAdapter.ArticleViewHolder>() {
    private val articleList: MutableList<Article> = ArrayList()

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPubDate = view.findViewById<TextView>(R.id.article_pub_date)
        var tvHeadline= view.findViewById<TextView>(R.id.article_headline)
        var tvSnippet= view.findViewById<TextView>(R.id.article_snippet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_article_result, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = articleList[position]
        holder.tvHeadline.text = article.headline?.main
        holder.tvSnippet.text = article.snippet
        holder.tvPubDate.text = article.publishDate
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            holder.tvPubDate.text = sdf.parse(article.publishDate).toString()
        } catch (e: Exception) {
            Log.e("publishDate", e.toString())
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setNewArticles(articles: List<Article>) {
        articleList.clear()
        articleList.addAll(articles)
    }

    fun addArticles(articles: List<Article>) {
        articleList.addAll(articles)
    }
}