package vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_group.view.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants.GROUP_EXTRA
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.GroupPrivacyConverter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.extension.loadImage
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupinfo.GroupInfoActivity

class SearchResultAdapter(private val searchResult: MutableList<Group>) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchResultViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false))

    override fun getItemCount(): Int = searchResult.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    internal fun addSearchResultToList(searchResult: List<Group>?) {
        if(searchResult != null) {
            this.searchResult.clear()
            this.searchResult.addAll(searchResult)
            notifyDataSetChanged()
        }
    }

    inner class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.group_cricle_image.setImageBitmap(null)
            itemView.groupNname_text_view.text = ""
            itemView.groupStatus_text_view.text = ""
        }

        fun onBind(position: Int) { //todo might be that I am not allowed to change data inside view component.
            val group = searchResult[position]
            val groupPrivacyConverter = GroupPrivacyConverter()
            inflateData(group.name, groupPrivacyConverter.getByCode(group.is_closed), group.photo_100)
            setItemClickListener(group)
        }

        private fun setItemClickListener(group: Group) {
            itemView.setOnClickListener { view: View ->
                view.context.startActivity(Intent(view.context, GroupInfoActivity::class.java).putExtra(GROUP_EXTRA, group))
            }
        }

        private fun inflateData(name: String?, isClosed: String?, imageUrl: String?) {
            name?.let { itemView.groupNname_text_view.text = it }
            imageUrl?.let { itemView.group_cricle_image.loadImage(imageUrl) }
            isClosed?.let { itemView.groupStatus_text_view.text = isClosed }
        }
    }
}