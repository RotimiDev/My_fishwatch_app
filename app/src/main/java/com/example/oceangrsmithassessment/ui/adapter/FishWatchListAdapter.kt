package com.example.oceangrsmithassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oceangrsmithassessment.R
import com.example.oceangrsmithassessment.data.model.FishWatch
import com.example.oceangrsmithassessment.ui.view.ListFragmentDirections

class FishWatchListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val diffCallBack = object : DiffUtil.ItemCallback<FishWatch>() {
        override fun areItemsTheSame(
            oldItem: FishWatch,
            newItem: FishWatch
        ): Boolean {
            return oldItem.speciesName == newItem.speciesName
        }

        override fun areContentsTheSame(
            oldItem: FishWatch,
            newItem: FishWatch
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RvViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fish_watch_list_item,
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RvViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class RvViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: FishWatch) = with(itemView) {
            val fishName = itemView.findViewById<TextView>(R.id.tvFishListName)
            val fishImage = itemView.findViewById<ImageView>(R.id.ivFishListImage)
            val fishHabitat = itemView.findViewById<TextView>(R.id.tvHabitatImpact)
            val fishCard = itemView.findViewById<CardView>(R.id.fishCardView)

            fishName.text = item.speciesName
            fishHabitat.text = item.habitatImpacts
            Glide.with(context).load(item.speciesIllustrationPhoto.src).into(fishImage)

            itemView.setOnClickListener {

                val action = ListFragmentDirections.actionListFragmentToListContentFragment(item)
                itemView.findNavController().navigate(action)
            }
        }
    }

}