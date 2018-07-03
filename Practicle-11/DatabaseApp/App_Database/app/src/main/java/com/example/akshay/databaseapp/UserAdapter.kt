package com.example.akshay.databaseapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class UserAdapter(val items : List<TableData>, val context: Context): RecyclerView.Adapter<ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView1.text = items[position].name
        holder.textView2.text = items[position].bDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val textView1 = itemView.findViewById<TextView>(R.id.textView3)
    val textView2 = itemView.findViewById<TextView>(R.id.textView4)
}

