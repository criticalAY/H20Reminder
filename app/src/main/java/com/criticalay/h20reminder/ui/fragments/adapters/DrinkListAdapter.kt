package com.criticalay.h20reminder.ui.fragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.model.Drink
import kotlinx.android.synthetic.main.water_item.view.*
import java.util.*

class DrinkListAdapter : RecyclerView.Adapter<DrinkListAdapter.MyDrinkHolder>(){
    private lateinit var calendar: Calendar

    private var drinkList = emptyList<Drink>()

    class MyDrinkHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDrinkHolder {

      return  MyDrinkHolder(LayoutInflater.from(parent.context).inflate(R.layout.water_item,parent,false))

    }

    override fun onBindViewHolder(holder: MyDrinkHolder, position: Int) {

        val currentItem = drinkList[position]
        holder.itemView.timeTv.text = currentItem.time
        holder.itemView.amountTv.text = currentItem.amount.toString()


    }

    override fun getItemCount(): Int {
        return drinkList.size

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(drink: List<Drink>){

        this.drinkList = drink
        notifyDataSetChanged()
    }
}