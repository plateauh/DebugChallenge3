package com.najed.debuggingchallenge3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.najed.debuggingchallenge3.databinding.ActivityWordDetailsBinding
import com.najed.debuggingchallenge3.databinding.DefinitionItemBinding
import com.najed.debuggingchallenge3.databinding.ItemRowBinding

class DefsAdapter(private var definitions: ArrayList<String>): RecyclerView.Adapter<DefsAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: DefinitionItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DefinitionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val definition = definitions[position]
        holder.binding.apply {
            defElementTv.text = definition
        }
    }

    override fun getItemCount() = definitions.size

    fun update(){
        notifyDataSetChanged()
    }
}