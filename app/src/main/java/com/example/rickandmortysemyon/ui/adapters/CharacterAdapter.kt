package com.example.rickandmortysemyon.ui.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortysemyon.R
import com.example.rickandmortysemyon.data.model.CharacterModel
import com.example.rickandmortysemyon.databinding.ItemCharacterBinding

class CharacterAdapter(private val listener: Listener) :
    PagingDataAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterModelComparator) {

    interface Listener {
        fun openInfoFragment(model: CharacterModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        val context: Context = holder.itemView.context
        val res: Resources = context.resources
        val margin = res.getDimensionPixelSize(R.dimen.margin_8)
        when (position) {
            0 -> holder.itemView.setPadding(0, margin, 0, 0)
            itemCount - 1 -> holder.itemView.setPadding(0, 0, 0, margin)
            else -> holder.itemView.setPadding(0, 0, 0, 0)
        }
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CharacterModel) {
            binding.nameTv.text = model.name
            binding.statusTv.text = model.status
            Glide.with(binding.root.context)
                .load(model.image)
                .into(binding.imageIv)

            binding.linearLayout.setOnClickListener { openInfoFragment(model) }

        }
    }

    private fun openInfoFragment(model: CharacterModel) {
        listener.openInfoFragment(model)
    }

    companion object {
        private val CharacterModelComparator = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return (oldItem is CharacterModel && newItem is CharacterModel && oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean = oldItem == newItem
        }
    }

}