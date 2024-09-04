package com.example.apptodo.adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptodo.databinding.TaskdetailsBinding
import com.example.apptodo.model.Model
import com.example.apptodo.weekcalenderitem

class TaskAdapter(var list: List<Model>): RecyclerView.Adapter<TaskAdapter.taskviewholder>() {
    class taskviewholder(val binding: TaskdetailsBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Model){
            binding.taskName.text=task.title
            binding.taskTime.text=task.Time

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskviewholder {
        val item = TaskdetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return taskviewholder(item)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: taskviewholder, position: Int) {
        holder.bind(list[position])


    }
    fun updatelist(list: List<Model>){
        this.list=list
        notifyDataSetChanged()


    }
}