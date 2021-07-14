package team.bum.ui.main.archive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.api.data.ResponseRewards
import team.bum.api.data.Reward
import team.bum.databinding.ItemCardRewardBinding
import team.bum.ui.main.archive.data.ArchiveRewardInfo
import kotlin.time.days

class ArchiveRewardAdapter : RecyclerView.Adapter<ArchiveRewardAdapter.ArchiveRewardViewHolder>() {

    private val archiveRewardInfo = mutableListOf<Reward>()
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
        fun onBind(archiveInfo: Reward, position: Int) {
            binding.apply {
                tvRewardDate.text = archiveInfo.created_date
                tvRewardContent.text = archiveInfo.sentence
                tvRewardAuthor.text = archiveInfo.author

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

    fun setItems(newItems: List<Reward>) {
        archiveRewardInfo.clear()
        archiveRewardInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(archiveInfo: Reward)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}