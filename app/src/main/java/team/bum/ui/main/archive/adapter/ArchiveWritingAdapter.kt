package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardMywritingBinding
import team.bum.ui.main.archive.data.ArchiveWritingInfo

class ArchiveWritingAdapter: RecyclerView.Adapter<ArchiveWritingAdapter.ArchiveWritingViewHolder>() {

    private val archiveWritingInfo = mutableListOf<ArchiveWritingInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveWritingViewHolder {
        val binding = ItemCardMywritingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveWritingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveWritingViewHolder, position: Int) {
        holder.onBind(archiveWritingInfo[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = archiveWritingInfo.size

    fun setItems(newItems: List<ArchiveWritingInfo>) {
        archiveWritingInfo.clear()
        archiveWritingInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class ArchiveWritingViewHolder(
        private val binding: ItemCardMywritingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveWritingInfo, context: Context) {
            binding.apply {
                tvMywritingCategory.text = archiveInfo.writingCategory
                tvMywritingTitle.text = archiveInfo.writingTitle
                tvMywritingContent.text = archiveInfo.writingContent
            }
        }
    }
}

