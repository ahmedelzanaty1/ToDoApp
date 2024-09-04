package com.example.apptodo.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.room.Database
import com.example.apptodo.Database.database
import com.example.apptodo.R
import com.example.apptodo.databinding.FragmentAddTaskBinding
import com.example.apptodo.model.Model
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var Binding: FragmentAddTaskBinding
    lateinit var calender: Calendar
    var isdateselected=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calender= Calendar.getInstance()
        initclick()

    }
    fun initclick(){
        Binding.submitTaskButton.setOnClickListener {

            if(valid()){
                val task = Model(null,Binding.enterTaskEdittext.text.toString(),null,calender.time,false,Binding.timeTextview.text.toString())
                database.getinstance(requireContext()).gerdao().inserttask(task)
                dismiss()
            }
        }
        Binding.selectTimeTextview.setOnClickListener {
            val TimePicker = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                isdateselected=true
                calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calender.set(Calendar.MINUTE, minute)
                Binding.timeTextview.text = "${hourOfDay}:${minute}"

            },
                calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), false)

            TimePicker.show()
        }
    }
    fun valid():Boolean{
        if(Binding.enterTaskEdittext.text.isEmpty()||Binding.enterTaskEdittext.text.isBlank()){
            Binding.enterTaskEdittext.error="enter task"
            return false
        }else
            Binding.enterTaskEdittext.error=null
        if(!isdateselected){
            Binding.timeTextview.error="select time"
            return false

        }
           return true

    }


}