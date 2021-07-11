package team.bum.ui.main.archive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardRewardBinding
import team.bum.ui.main.archive.data.ArchiveRewardInfo

class ArchiveRewardAdapter : RecyclerView.Adapter<ArchiveRewardAdapter.ArchiveRewardViewHolder>() {

    private val archiveRewardInfo = mutableListOf<ArchiveRewardInfo>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveRewardViewHolder {
        val binding =
            ItemCardRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveRewardViewHolder(binding)
    }

    inner class ArchiveRewardViewHolder(
        private val binding: ItemCardRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveRewardInfo, position: Int) {
            binding.apply {
                tvRewardDate.text = archiveInfo.rewardDate
                tvRewardContent.text = archiveInfo.rewardContent
                tvRewardAuthor.text = archiveInfo.rewardAuthor

                itemView.setOnClickListener {
                    itemClickListener.onClick(archiveRewardInfo[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = archiveRewardInfo.size

    override fun onBindViewHolder(holder: ArchiveRewardViewHolder, position: Int) {
        holder.onBind(archiveRewardInfo[position], position)
    }

    fun setItems(newItems: List<ArchiveRewardInfo>) {
        archiveRewardInfo.clear()
        archiveRewardInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(archiveInfo: ArchiveRewardInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}