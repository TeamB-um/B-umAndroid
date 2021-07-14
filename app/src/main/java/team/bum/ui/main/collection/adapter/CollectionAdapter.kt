package team.bum.ui.main.collection.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.bum.R
import team.bum.ui.main.collection.data.CategoryInfo
import team.bum.databinding.ItemCollectionBinding

class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private val categoryInfo = mutableListOf<CategoryInfo>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionViewHolder {
        val binding =
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(categoryInfo[position], holder.itemView.context)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = categoryInfo.size

    fun setItems(newItems: List<CategoryInfo>) {
        categoryInfo.clear()
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class CollectionViewHolder(
        private val binding: ItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun onBind(categoryInfo: CategoryInfo, context: Context) {
            Glide.with(context).load(categoryInfo.img).into(binding.imageCollection)
            binding.apply {
                tvCategory.text = categoryInfo.name
               when (categoryInfo.index) {
                   0 -> tvCategory.setTextColor(R.color.blue_2_main)
                   1 -> tvCategory.setTextColor(R.color.green_2_main)
                   2 -> tvCategory.setTextColor(R.color.pink_2_main)
                   3 -> tvCategory.setTextColor(R.color.blue_4)
                   4 -> tvCategory.setTextColor(R.color.green_3)
                   5 -> tvCategory.setTextColor(R.color.green_4)
                   else -> tvCategory.setTextColor(R.color.black)
               }
            }
        }
    }
}