package vkstatistic.apoyark.com.vkstatistics.ui.search.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_group.view.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.utils.extension.loadImage

class SearchResultAdapter(private val searchResult: MutableList<Group>) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchResultViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false))

    override fun getItemCount(): Int = searchResult.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    internal fun addSearchResultToList(searchResult: List<Group>) {
        this.searchResult.addAll(searchResult)
        notifyDataSetChanged()
    }

    inner class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.group_cricle_image.setImageBitmap(null)
            itemView.group_name_text_view.text = ""
            itemView.members_count_count_text_view.text = ""
        }

        fun onBind(position: Int) {
            val group = searchResult[position]
            inflateData(group.name, group.photo_50)
            //TODO set on click listener

        }

        private fun inflateData(name: String?, imageUrl: String?) {
            name?.let { itemView.group_name_text_view.text = it }
            imageUrl?.let { itemView.group_cricle_image.loadImage(imageUrl) }
        }
    }
}