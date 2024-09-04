package com.example.apptodo.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.apptodo.Database.database
import com.example.apptodo.R
import com.example.apptodo.adaptar.TaskAdapter
import com.example.apptodo.databinding.FragmentListBinding
import com.example.apptodo.weekcalenderitem
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale


class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var adapter: TaskAdapter
    var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        gettask()

    }

    fun init() {
        bindcalender()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val endDate = LocalDate.now()
            val currentDate = LocalDate.now()
            val currentMonth = YearMonth.now()
            val startDate = currentMonth.minusMonths(100).atStartOfMonth() // Adjust as needed

            currentMonth.plusMonths(100).atEndOfMonth()
            val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
            binding.calenderview.setup(startDate, endDate, firstDayOfWeek)
            binding.calenderview.scrollToWeek(currentDate)
        }

    }
    fun gettask() {
        val tasks = database.getinstance(requireContext()).gerdao().getalltask()
        adapter = TaskAdapter(tasks)
        binding.recyclerview.adapter = TaskAdapter(tasks)


    }

    fun bindcalender() {
        binding.calenderview.dayBinder = object : WeekDayBinder<weekcalenderitem> {
            @SuppressLint("SuspiciousIndentation")
            override fun bind(container: weekcalenderitem, data: WeekDay) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    var blue = ResourcesCompat.getColor(resources, R.color.blue, null)
                    var black =  ResourcesCompat.getColor(resources, R.color.black, null)
                    container.textViewDayName.text = data.date.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    )
                    container.textViewDayNumber.text = "${data.date.dayOfMonth}"
                    if (selectedDate == data.date) {
                        container.textViewDayNumber.setTextColor(blue)
                        container.textViewDayName.setTextColor(blue)
                    }else
                        container.textViewDayNumber.setTextColor(black)
                        container.textViewDayName.setTextColor(black)
                    container.view.setOnClickListener{
                        selectedDate = data.date
                        binding.calenderview.notifyWeekChanged(data)
                    }
                }
            }
            fun filterdate(date:Date){
                val tasks = database.getinstance(requireContext()).gerdao().gettaskbyid(date)
                adapter.updatelist(tasks)


            }

            override fun create(view: View): weekcalenderitem {
                return weekcalenderitem(view)
            }


        }

    }
}

