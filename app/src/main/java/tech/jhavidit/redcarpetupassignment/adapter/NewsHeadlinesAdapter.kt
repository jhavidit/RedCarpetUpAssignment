package tech.jhavidit.redcarpetupassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import tech.jhavidit.redcarpetupassignment.R
import tech.jhavidit.redcarpetupassignment.model.Article
import tech.jhavidit.redcarpetupassignment.util.getPeriod
import tech.jhavidit.redcarpetupassignment.util.globalTimeDateFormat
import tech.jhavidit.redcarpetupassignment.util.toDateFormat

class NewsHeadlinesAdapter(private val context: Context) :
    RecyclerView.Adapter<NewsHeadlinesAdapter.ViewHolder>() {

    private var list: List<Article> = ArrayList()

    fun setMusicItem(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = list[position]
        holder.articleHeadline.text = item.title

        var publish = item.publishedAt
        var author = item.author
        if(author.isNullOrEmpty())
        {
            author = "Publisher unknown"
        }
        holder.articleAuthor.text = author
        if(!publish.isNullOrEmpty())
        {
            publish = getPeriod(globalTimeDateFormat(publish)!!)
        }
        if(!publish.isNullOrEmpty())

         publish = "Published : $publish"
        else
            publish = "Published : Unknown"
        holder.date.text = publish

        Glide.with(context)
            .load(item.urlToImage)
            .into(holder.articleImage)
        holder.news.setOnClickListener {
            val bundle = bundleOf(
                "author" to item.author,
                "description" to item.description,
                "title" to item.title,
                "source" to item.source,
                "url" to item.url,
                "photo" to item.urlToImage,
                "date" to item.publishedAt


            )
            it.findNavController()
                .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val news: CardView = view.news
        val articleHeadline: TextView = view.heading
        val articleImage: ImageView = view.article_photo
        val articleAuthor: TextView = view.author
        val date: TextView = view.date

    }
}