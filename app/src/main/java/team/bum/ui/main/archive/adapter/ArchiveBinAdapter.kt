package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardBinBinding
import team.bum.ui.main.archive.data.ArchiveBinInfo

class ArchiveBinAdapter: RecyclerView.Adapter<ArchiveBinAdapter.ArchiveBinViewHolder>() {

    private val archiveBinInfo = mutableListOf<ArchiveBinInfo>()

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

    fun setItems(newItems: List<ArchiveBinInfo>) {
        archiveBinInfo.clear()
        archiveBinInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class ArchiveBinViewHolder(
        private val binding: ItemCardBinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveBinInfo, context: Context) {
            binding.apply {
                tvBinCategory.text = archiveInfo.binCategory
                tvBinTitle.text = archiveInfo.binTitle
                tvBinContent.text = archiveInfo.binContent
                tvBinDday.text = archiveInfo.binDday
            }
        }
    }
}

