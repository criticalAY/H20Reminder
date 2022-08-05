package com.criticalay.h20reminder.ui.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.databinding.FragmentHomeBinding
import com.criticalay.h20reminder.model.Notification
import com.criticalay.h20reminder.utils.AlarmReciever
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private var progr = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        return binding.root




      //  progress_bar.progress = 20



    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){

            val name : CharSequence = "H20 Reminder Channel"
            val description = "Channel for Water Reminder"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("H2O Reminder", name, importance)
            channel.description = description
            val NotificationManager = getSystemService(Context,NotificationManager::class.java)

        }
    }

    private fun onButtonClicked() {



    }


    private fun updateProgressBar() {
    progress_bar.progress = progr
    text_view_progress.text = "$progr%"
}



}