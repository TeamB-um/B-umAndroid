package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.api.data.Trashcan
import team.bum.databinding.ItemCardBinBinding

class ArchiveBinAdapter: RecyclerView.Adapter<ArchiveBinAdapter.ArchiveBinViewHolder>() {

    private val archiveBinInfo = mutableListOf<Trashcan>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveBinViewHolder {
        val binding = ItemCardBinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveBinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveBinViewHolder, position: Int) {
        holder.onBind(archiveBinInfo[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = archiveBinInfo.size

    fun setItems(newItems: List<Trashcan>) {
        archiveBinInfo.clear()
        archiveBinInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class ArchiveBinViewHolder(
        private val binding: ItemCardBinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: Trashcan, context: Context) {
            binding.apply {
                tvBinCategory.text = archiveInfo.category.name
                tvBinTitle.text = archiveInfo.title
                tvBinContent.text = archiveInfo.text
                tvBinDday.text = archiveInfo.d_day.toString()
                when (archiveInfo.category.index) {
                    0 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.blue_2_main))
                    1 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.green_3))
                    2 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.pink_3))
                    3 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.blue_4))
                    4 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.green_5))
                    5 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.green_2_main))
                    6 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.blue_3))
                    7 -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.pink_2_main))
                    else -> tvBinCategory.setTextColor(ContextCompat.getColor(context, R.color.text_grey))
                }
            }
        }
    }
}

