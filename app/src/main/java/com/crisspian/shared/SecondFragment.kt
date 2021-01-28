package com.crisspian.shared

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.crisspian.shared.databinding.FragmentSecondBinding
import com.crisspian.shared.model.Task

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    //referencia al view model
    private val viewModel:TaskViewModel by activityViewModels()
    private var idTask : Int =0
    private var taskSelect : Task?=null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItem().observe(viewLifecycleOwner,{
            it.let {
                binding.edTitulo.setText(it.title)
                binding.edDescription.setText(it.taskDescription)
                binding.edFecha.setText(it.date)
                binding.priority.setText(it.priority)
                binding.checkBox.isChecked= it.state

                //ver id de cada elemento
                Log.d("id",it.id.toString())
                idTask=it.id
                taskSelect=it
            }
        })
        binding.btnGuardar.setOnClickListener {
            saveData()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding
        }

    fun saveData(){
        val title =binding.edTitulo.text.toString()
        val description = binding.edDescription.text.toString()
        val date= binding.edFecha.text.toString()
        //transformar el valor a un entero toInt
        val priority =binding.priority.text.toString().toInt()
        val state = binding.checkBox.isChecked

        //validación

        if (title.isEmpty() && description.isEmpty() && date.isEmpty()){
            Toast.makeText(context,"debes añadir datos",Toast.LENGTH_LONG).show()
        }else{
            if (idTask == 0) {
                val newTask = Task( title = title,
                        taskDescription = description,
                        date = date,
                        priority = priority,
                        state = state)
                viewModel.insertTask(newTask)
            } else {
                val newTask = Task(
                        id = idTask,
                        title = title,
                        taskDescription = description,
                        date = date,
                        priority = priority,
                        state = state)
                viewModel.insertTask(newTask)
    }}}
}