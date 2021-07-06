package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardMywritingBinding
import team.bum.ui.main.archive.data.ArchiveInfo

class ArchiveAdapter: RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>() {

    private val archiveInfo = mutableListOf<ArchiveInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveViewHolder {
        val binding = ItemCardMywritingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        holder.onBind(archiveInfo[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = archiveInfo.size

    fun setItems(newItems: List<ArchiveInfo>) {
        archiveInfo.clear()
        archiveInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class ArchiveViewHolder(
        private val binding: ItemCardMywritingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveInfo, context: Context) {
            binding.apply {
                tvMywritingCategory.text = archiveInfo.category
                tvMywritingTitle.text = archiveInfo.title
                tvMywritingContent.text = archiveInfo.content
            }
        }
    }
}

