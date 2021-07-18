package team.bum.ui.main.collection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.bum.R
import team.bum.api.data.Category
import team.bum.databinding.ItemCollectionBinding
import team.bum.ui.main.archive.writing.ArchiveWritingFragment

class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private val categoryInfo = mutableListOf<Category>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionViewHolder {
        val binding =
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    inner class CollectionViewHolder(
        private val binding: ItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoryInfo: Category, context: Context, position: Int) {
            Glide.with(context).load(categoryInfo.img).into(binding.imageCollection)
            binding.apply {
                tvCategory.text = categoryInfo.name
                team.bum.model.Category.values().forEach {
                    if (categoryInfo.index == it.id)
                        tvCategory.setTextColor(ContextCompat.getColor(context, it.textColor))
                }
                itemView.setOnClickListener {
                    itemClickListener.onClick(position, categoryInfo.name, categoryInfo.count)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(categoryInfo[position], holder.itemView.context, position)
    }

    override fun getItemCount(): Int = categoryInfo.size

    fun setItems(newItems: List<Category>) {
        categoryInfo.clear()
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Category>) {
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(position: Int, categoryName: String, categoryCount: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}