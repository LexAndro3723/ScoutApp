package ru.example.scout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.example.scout.R
import ru.example.scout.SettingsListener
import ru.example.scout.databinding.ListItemBinding
import ru.example.scout.retrofit.Setting

class SettingAdapter(
    private val SettingsListener: SettingsListener
) : ListAdapter<Setting, SettingAdapter.Holder>(Comparator()) {

    class Holder(
        view: View,
        private val SettingsListener: SettingsListener
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        init {
            view.setOnClickListener {
                if (adapterPosition >= 0) {
                    SettingsListener.onItemClick(adapterPosition)
                }
            }

            view.setOnLongClickListener {
                if (adapterPosition >= 0) {
                    SettingsListener.onLongClick(adapterPosition)
                }
                return@setOnLongClickListener true
            }
        }

        fun bind(setting: Setting) = with(binding) {
            name.text = setting.name
            label.text = setting.label
        }
    }

    class Comparator : DiffUtil.ItemCallback<Setting>() {
        override fun areItemsTheSame(oldItem: Setting, newItem: Setting): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Setting, newItem: Setting): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view, SettingsListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}