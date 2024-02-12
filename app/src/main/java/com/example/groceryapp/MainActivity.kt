package com.example.groceryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryRVAdapter.GroceryItemClickInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var flgBtn: FloatingActionButton
    private lateinit var list: List<GroceryItems>
    private lateinit var groceryRVAdapter: GroceryRVAdapter
    private lateinit var groceryViewModel: GroceryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flgBtn = findViewById(R.id.idFABAdd)
        recyclerView = findViewById(R.id.idRVItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        list = ArrayList<GroceryItems>()

        groceryRVAdapter = GroceryRVAdapter(list, this)
        recyclerView.adapter = groceryRVAdapter

        val groceryRepository = GroceryRepository(GroceryDatabase.getDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)


        // when we use live data

        groceryViewModel.getAllGroceryItems().observe(this, Observer {
            groceryRVAdapter = GroceryRVAdapter(it, this)
            recyclerView.adapter = groceryRVAdapter
            groceryRVAdapter.notifyDataSetChanged()
        })


        flgBtn.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialogue)

        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemNameEdt = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.idEditItemPrice)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName : String = itemNameEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val Qty : Int = itemQuantity.toInt()
            val Prc : Int = itemPrice.toInt()

            if(itemName.isNotEmpty() && itemQuantity.isNotEmpty() && itemPrice.isNotEmpty()){
                val items = GroceryItems(0, itemName,Qty,Prc)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted...",Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please Enter All The Data.",Toast.LENGTH_SHORT).show()

            }
        }
        // to show the dialog

        dialog.show()
    }

    override fun onClickItem(items: GroceryItems) {
        groceryViewModel.delete(items)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted...", Toast.LENGTH_SHORT).show()
    }
}