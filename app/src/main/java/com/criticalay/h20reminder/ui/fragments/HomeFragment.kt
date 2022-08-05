package com.criticalay.h20reminder.ui.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.FragmentManager
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.databinding.FragmentHomeBinding
import com.criticalay.h20reminder.model.Notification
import com.criticalay.h20reminder.utils.AlarmReciever
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {
    private var progr = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var picker :MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //onButtonClicked()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate view and obtain an instance of the binding class
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
      //  text_view_progress.setText("fdfdfd")
        _binding!!.progressBar.progress =10
        _binding!!.addCustomDrinkBtn.setOnClickListener{

            Toast.makeText(context, "hello add custom Drink", Toast.LENGTH_SHORT).show()


        }


        createNotificationChannel()

        _binding!!.addCustomAlarm.setOnClickListener{

            showTimePicker()



        }

        return binding.root




      //  progress_bar.progress = 20



    }

    private fun showTimePicker() {

        picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Reminder Time")
            .build()

        picker.show(parentFragmentManager,"H20Reminder")

        picker.addOnPositiveButtonClickListener {

           calendar= Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0


            alarmManager =  context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,AlarmReciever::class.java)
            pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)

            alarmManager.setRepeating(

                AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,pendingIntent
            )
            Toast.makeText(context, "Reminder Set Successfully", Toast.LENGTH_SHORT).show()





        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){

            val name : CharSequence = "H20Reminder"
            val description = "Channel for Water Reminder"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("H2OReminder", name, importance)
            channel.description = description
            val NotificationManager = getSystemService(requireContext(), NotificationManager::class.java) as NotificationManager
            NotificationManager.createNotificationChannel(channel)

        }
    }

    private fun onButtonClicked() {



    }


    private fun updateProgressBar() {
    progress_bar.progress = progr
    text_view_progress.text = "$progr%"
}



}