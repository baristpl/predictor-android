package com.deu.csc.predictor.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.deu.csc.predictor.databinding.ItemTableRowBinding
import com.deu.csc.predictor.databinding.ItemTextBinding

class TextAdapter(
    private val data: List<String>,
    private val onClick: (String) -> Unit,
    private val viewType: ViewType = ViewType.Default,
) : RecyclerView.Adapter<TextAdapter.BaseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (this.viewType) {
            ViewType.Default -> DefaultHolder(
                ItemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewType.Table -> YearHolder(
                ItemTableRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(position)
    }

    abstract class BaseHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(position: Int)
    }

    inner class DefaultHolder(private val binding: ItemTextBinding) : BaseHolder(binding) {
        override fun bind(position: Int) {
            val text = data[position]
            binding.name.text = text
            binding.root.setOnClickListener {
                onClick.invoke(text)
            }
        }
    }

    inner class YearHolder(private val binding: ItemTableRowBinding) : BaseHolder(binding) {
        override fun bind(position: Int) {
            binding.name.text = specNames[position]
            binding.value.text = data[position]
        }
    }

    enum class ViewType {
        Default, Table
    }

    private val specNames = listOf("Marka :", "Seri :", "Model :", "Yıl :", "Kilometre :", "Güç :", "Vites Tipi :", "Yıllık Mtv :", "Garanti")

}