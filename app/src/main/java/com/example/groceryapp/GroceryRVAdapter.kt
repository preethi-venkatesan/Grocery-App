package com.example.groceryapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class GroceryRVAdapter(
    var list: List<GroceryItems>, var groceryItemClickInterface: GroceryItemClickInterface
) :RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>(){


    interface GroceryItemClickInterface {
        fun onClickItem(items: GroceryItems)
    }

    class GroceryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        var quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        var rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        var totalCostTV = itemView.findViewById<TextView>(R.id.idTVHeading)
        var amtTV = itemView.findViewById<TextView>(R.id.idTVAmt)
        var deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryRVAdapter.GroceryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item,parent,false)
        return GroceryViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GroceryRVAdapter.GroceryViewHolder, position: Int) {
        val currentItem = list[position]
        holder.nameTV.text= currentItem.itemName.toString()
        holder.quantityTV.text= currentItem.itemQuantity.toString()
        holder.rateTV.text= "Rs. "+ currentItem.itemPrice.toString()
        holder.amtTV.text= "Rs. " + (currentItem.itemPrice*currentItem.itemQuantity).toString()
        holder.deleteIV.setOnClickListener {

            groceryItemClickInterface.onClickItem(list[position])
        }

    }

    override fun getItemCount(): Int {
      return list.size
    }
}