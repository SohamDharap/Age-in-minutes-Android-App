package com.soham.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate: TextView? = null
    private var minutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.SelectedDate)
        minutes = findViewById(R.id.minutes)

        datePicker.setOnClickListener{
            clickDateListener()
        }

    }
    private fun clickDateListener(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, SelectedYear, SelectedMonth, SelectedDayOfMonth->
                Toast.makeText(this, "Year was $SelectedYear month was ${SelectedMonth+1} and day was $SelectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selected = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"

                selectedDate?.text = selected

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selected)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        minutes?.text = differenceInMinutes.toString()
                    }

                  }



            },
            year,
            month,
            day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}