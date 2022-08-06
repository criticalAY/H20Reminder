package com.criticalay.h20reminder.ui.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*

import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.databinding.FragmentHomeBinding
import com.criticalay.h20reminder.databinding.FragmentHomeBinding.inflate
import com.criticalay.h20reminder.model.Drink
import com.criticalay.h20reminder.model.DrinkViewModel
import com.criticalay.h20reminder.model.Notification
import com.criticalay.h20reminder.ui.fragments.adapters.DrinkListAdapter
import com.criticalay.h20reminder.utils.AlarmReciever
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {
    private var size : String= "200"
    private var progr = 0

    private lateinit var mDrinkViewModel  : DrinkViewModel



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
        _binding!!.progressBar.progress =50

             val adapter = DrinkListAdapter()
        val recyclerView = _binding!!.recyclerViewRecord
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())





        mDrinkViewModel= ViewModelProvider(this)[DrinkViewModel::class.java]

        mDrinkViewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { user->
            adapter.setData(user)

        })

        _binding!!.DrunkBtn.setOnClickListener{


            insertDrinkDataToDatabase()
        }

        _binding!!.addCustomDrinkBtn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

               size = parent?.getItemAtPosition(position).toString()

                Toast.makeText(
                    context,
                    "You have Selected ${parent?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }









        createNotificationChannel()

        _binding!!.addCustomAlarm.setOnClickListener{

            showTimePicker()



        }

        return binding.root




      //  progress_bar.progress = 20



    }

    private fun insertDrinkDataToDatabase() {

     val id = 0
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
      //  System.out.println(" C DATE is  $currentDate")
        val hr = Calendar.HOUR_OF_DAY
        val min = Calendar.MINUTE
        val tt = Calendar.AM_PM
        val ttt= "$hr:$min $tt"

        val date = Calendar.DATE.toString()
        val time =currentDate.toString()
        var drink : String= "Apple"
        val vol = size.toInt()
      //  val vol = 200

        val drunk = Drink(id, date, time,drink,vol)
        mDrinkViewModel.addDrink(drunk)
        Toast.makeText(context, "successfully added yout drink data", Toast.LENGTH_SHORT).show()




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


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
      val inflater: MenuInflater = MenuInflater(context)
    inflater.inflate(R.menu.water_menu, menu)
        //return true


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

                R.id.cup100ml -> Toast.makeText(context, "hello 100", Toast.LENGTH_SHORT).show()
            R.id.cup125ml -> Toast.makeText(context, "hello 125", Toast.LENGTH_SHORT).show()
            R.id.cup150ml -> Toast.makeText(context, "hello 150", Toast.LENGTH_SHORT).show()


        }


        return super.onContextItemSelected(item)
    }



}