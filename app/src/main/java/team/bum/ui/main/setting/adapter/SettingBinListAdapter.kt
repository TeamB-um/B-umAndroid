package team.bum.ui.main.setting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.api.data.Category
import team.bum.databinding.ItemCategoryBinding

class SettingBinListAdapter : RecyclerView.Adapter<SettingBinListAdapter.SettingBinListViewHolder>() {

    private val categoryInfo = mutableListOf<Category>()
    private lateinit var clickListener: CliCkListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingBinListViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingBinListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingBinListViewHolder, position: Int) {
        holder.onBind(categoryInfo[position], position)
    }

    fun setItems(newItems: List<Category>) {
        categoryInfo.clear()
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categoryInfo.size

    inner class SettingBinListViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: Category, position: Int) {
            binding.apply {
                tvCategory.text = category.name
                tvCategory.setOnClickListener {
                    clickListener.onClickText(categoryInfo[position])
                }
                imageDelete.setOnClickListener {
                    clickListener.onClickDelete(categoryInfo[position])
                }
            }
        }
    }

    interface CliCkListener {
        fun onClickDelete(categoryInfo: Category)
        fun onClickText(categoryInfo: Category)
    }

    fun setClickListener(clickListener: CliCkListener) {
        this.clickListener = clickListener
    }
}

