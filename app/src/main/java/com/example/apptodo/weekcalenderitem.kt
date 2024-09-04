package com.example.apptodo

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer

class weekcalenderitem( private val calenderview: View) :ViewContainer(calenderview) {
    val textViewDayName = calenderview.findViewById<TextView>(R.id.textday)
    val textViewDayNumber = calenderview.findViewById<TextView>(R.id.numberday)

}