package com.crisspian.shared.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.crisspian.shared.databinding.TaskItemBinding

class TaskAdapter :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var mListTask = listOf<Task>()

    //nos permite meter un objeto en un live data ,esto para hacer click en un elemnto y capturarlo
    private val selectTask = MutableLiveData<Task>()
    //metodo para obtener valor en first fragment
    fun selectItem () =MutableLiveData<Task>()

    fun update (lisTask: List<Task>){
        mListTask= lisTask
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
//se unen las vistas por su posición
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = mListTask[position]
        holder.bind(task)
    }

    //limite de objetos

    override fun getItemCount(): Int =mListTask.size

//crear clase interna inner , extender view.Onclicklistener para importar metodo onCLick
inner class TaskViewHolder(private val binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    fun bind(task: Task) {
        binding.tvDate.text = task.date
        binding.cbState.isChecked = task.state
        binding.tvDescription.text=task.taskDescription
        binding.tvTitle.text=task.title
        //este item es parte de View Holder , captura toda la pantalla
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        selectTask.value=mListTask[adapterPosition]
    }

}
}