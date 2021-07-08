package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardMywritingBinding
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.util.setInvisible
import team.bum.util.setVisible

class ArchiveWritingAdapter: RecyclerView.Adapter<ArchiveWritingAdapter.ArchiveWritingViewHolder>() {

    private val archiveWritingInfo = mutableListOf<ArchiveWritingInfo>()
    private var itemViewMode = MODE_NORMAL

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveWritingViewHolder {
        val binding = ItemCardMywritingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.checkCircle.apply {
            if (viewType == MODE_NORMAL) setInvisible()
            if (viewType == MODE_SELECT) setVisible()
         }
        return ArchiveWritingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewMode
    }

    override fun getItemCount(): Int = archiveWritingInfo.size

    override fun onBindViewHolder(holder: ArchiveWritingViewHolder, position: Int) {
        holder.onBind(archiveWritingInfo[position], holder.itemView.context)
    }

    fun setItems(newItems: List<ArchiveWritingInfo>) {
        archiveWritingInfo.clear()
        archiveWritingInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setViewMode(mode: Int) {
        itemViewMode = mode
        notifyDataSetChanged()
    }

    fun ItemCardMywritingBinding.changeMode(isSelectMode: Boolean = false) {
        this.checkCircle.apply {
            if (isSelectMode) setVisible() else setInvisible()
        }
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

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_SELECT = 1
    }
}

