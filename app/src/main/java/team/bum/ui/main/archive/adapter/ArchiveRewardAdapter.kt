package team.bum.ui.main.archive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardRewardBinding
import team.bum.ui.main.archive.data.ArchiveRewardInfo

class ArchiveRewardAdapter: RecyclerView.Adapter<ArchiveRewardAdapter.ArchiveRewardViewHolder>() {

    private val archiveRewardInfo = mutableListOf<ArchiveRewardInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveRewardViewHolder {
        val binding = ItemCardRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveRewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveRewardViewHolder, position: Int) {
        holder.onBind(archiveRewardInfo[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = archiveRewardInfo.size

    fun setItems(newItems: List<ArchiveRewardInfo>) {
        archiveRewardInfo.clear()
        archiveRewardInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class ArchiveRewardViewHolder(
        private val binding: ItemCardRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveRewardInfo, context: Context) {
            binding.apply {
                tvRewardDate.text = archiveInfo.rewardDate
                tvRewardContent.text = archiveInfo.rewardContent
                tvRewardAuthor.text = archiveInfo.rewardDate
            }
        }
    }
}

