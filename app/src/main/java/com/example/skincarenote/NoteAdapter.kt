package com.example.skincarenote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skincarenote.databinding.ItemNoteBinding

class NoteAdapter(private val list: List<Note>,
                  private val onEdit: (Note) -> Unit,
                  private val onDelete: (Note) -> Unit,

) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = list[position]

        holder.binding.name.text = note.name
        holder.binding.address.text = note.address
        holder.binding.phone.text = note.phone
        holder.binding.editBtn.setOnClickListener {
            onEdit(note)
        }
        holder.binding.deleteBtn.setOnClickListener {
            onDelete(note)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}