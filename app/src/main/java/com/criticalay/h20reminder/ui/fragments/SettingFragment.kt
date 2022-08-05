package com.criticalay.h20reminder.ui.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.MessageFormat.format
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.databinding.FragmentHomeBinding
import com.criticalay.h20reminder.databinding.FragmentSettingBinding
import com.criticalay.h20reminder.utils.AlarmReciever
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_setting.*
import java.text.MessageFormat.format
import java.time.LocalTime
import java.util.*


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var picker : MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        _binding!!.notificationSwitch.setOnClickListener{

            notificationSettings()

        }

        _binding!!.WakeUpTime.setOnClickListener{

            SetCustomTimePickerDay()
        }
        _binding!!.SleepTime.setOnClickListener{
            SetCustomTimePickerNight()


    }




        return binding.root
    }

    ////making the notification on off layout visible/invisible
    private fun notificationSettings() {
        sleepTimeLayout.isVisible = notificationSwitch.isChecked

        if(!sleepTimeLayout.isVisible){
            alarmManager.cancel(pendingIntent)



        }


    }

    @SuppressLint("SetTextI18n")
    private fun SetCustomTimePickerDay() {
        picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Reminder Time")
            .build()

        picker.show(parentFragmentManager,"H20Reminder")




        picker.addOnPositiveButtonClickListener {

            if(picker.hour>12 ){

                _binding!!.WakeUpTime.text =
                    String.format("%02d",picker.hour-12) + ":" + String.format("%02d",picker.minute)+ " PM"
            }

            else{

                _binding!!.WakeUpTime.text =
                    String.format("%02d",picker.hour) + ":" + String.format("%02d",picker.minute)+ " AM"
            }


            calendar= Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

            AlarmSch()



    }


    }

    @SuppressLint("SetTextI18n")
    private fun SetCustomTimePickerNight() {
        picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Reminder Time")
            .build()

        picker.show(parentFragmentManager,"H20Reminder")




        picker.addOnPositiveButtonClickListener {

            if(picker.hour>12 ){

                _binding!!.SleepTime.text =
                    String.format("%02d",picker.hour-12) + ":" + String.format("%02d",picker.minute)+ " PM"
            }

            else{

                _binding!!.SleepTime.text =
                    String.format("%02d",picker.hour) + ":" + String.format("%02d",picker.minute)+ " AM"
            }


            calendar= Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }



        if(calendar.time == SleepTime) {

            alarmManager.cancel(pendingIntent)
        }

    }

    private fun AlarmSch(){

        alarmManager =  context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReciever::class.java)
        pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)

        alarmManager.setRepeating(

            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_HOUR,pendingIntent

        )


    }





}