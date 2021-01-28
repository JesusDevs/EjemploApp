package com.crisspian.shared

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.shared.databinding.FragmentFirstBinding
import com.crisspian.shared.model.Task
import com.crisspian.shared.model.TaskAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel:TaskViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instanciar el adapter y pasarlo al rv
        val adapter=TaskAdapter()
        binding.rvView.adapter=adapter
        binding.rvView.layoutManager = LinearLayoutManager(context)

        val task = Task(1,"mi primera tarea",
        "esta es una tarea de prueba 1",
        "27-01-2021",2,false)

        val task2 = Task(1,"second tarea",
            "esta es una tarea de prueba 2",
            "27-01-2021",2,false)


        viewModel.insertTask(task)
        viewModel.insertTask(task2)

        //esto esta observando al objeto expuesto en view model
        viewModel.allTask.observe(viewLifecycleOwner,{
            it.let{
          adapter.update(it)}

        //REFERENCIA AL FAB ,pasar a segundo fragmento
            binding.fab.setOnClickListener{
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            adapter.selectItem().observe(viewLifecycleOwner,){
              //sirve por si el dato llega nulo , solo devuelve
            // el update si el elemento no es null
            it?.let{
            Log.d("item ",it.title)
                viewModel.selectedItem()
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }}
        })
    }
}