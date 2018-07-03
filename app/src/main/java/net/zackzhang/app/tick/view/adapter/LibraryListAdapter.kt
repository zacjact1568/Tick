package net.zackzhang.app.tick.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_library.*
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.model.bean.Library
import net.zackzhang.app.tick.util.SystemUtil

class LibraryListAdapter(private val mActivity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LIBRARIES = arrayOf(
            Library("Android Support Libraries", "Android", "https://developer.android.com/topic/libraries/support-library/"),
            Library("ButterKnife", "JakeWharton", "http://jakewharton.github.io/butterknife/"),
            Library("Kotlin", "JetBrains", "https://kotlinlang.org")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_library, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        val (name, developer, link) = LIBRARIES[position]
        itemViewHolder.vNameText.text = name
        itemViewHolder.vDeveloperText.text = developer
        itemViewHolder.itemView.setOnClickListener { SystemUtil.openLink(link, mActivity) }
    }

    override fun getItemCount() = LIBRARIES.size

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}
