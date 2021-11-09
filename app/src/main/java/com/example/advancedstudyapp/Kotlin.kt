package com.example.advancedstudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedstudyapp.adapter.RecyclerViewAdapter
import com.example.advancedstudyapp.dataBase.DBHlr2
import com.example.advancedstudyapp.dataModel.Note

class Kotlin : AppCompatActivity() {
    var list = ArrayList<Note>()
    lateinit var myRv : RecyclerView
    lateinit var dbhlr: DBHlr2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setTitle("Kotlin Review")
        var btnAdd = findViewById<Button>(R.id.button3)
        dbhlr = DBHlr2(this)

        btnAdd.setOnClickListener {
            val dialogBuilder = android.app.AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_dialog, null)
            dialogBuilder.setTitle("Alert Dialog")
            dialogBuilder.setView(dialogView)
            val edtitle = dialogView.findViewById<EditText>(R.id.edtitle1)
            val edmore = dialogView.findViewById<EditText>(R.id.edmore1)
            val eddes = dialogView.findViewById<EditText>(R.id.eddes1)
            val tvBtn = dialogView.findViewById<Button>(R.id.button6)
            tvBtn.setOnClickListener {
                if (edtitle.text.isEmpty() || edmore.text.isEmpty() || eddes.text.isEmpty())
                    Toast.makeText(this, "Fill all fields please", Toast.LENGTH_SHORT).show()
                else {
                    var title = edtitle.text.toString()
                    var expl = edmore.text.toString()
                    var des = eddes.text.toString()
                    dbhlr.savedata("kotlin", title, expl, des)
                    Toast.makeText(this, "added successfully", Toast.LENGTH_SHORT).show()
                    myRv()
                }
            }
            dialogBuilder.show()
        }
        myRv = findViewById<RecyclerView>(R.id.myRv)
        myRv()
    }

    fun update(s1:String,s2:String, s3:String){
        val dialogBuilder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setTitle("Alert Dialog")
        dialogBuilder.setView(dialogView)
        val edtitle = dialogView.findViewById<EditText>(R.id.edtitle2)
        val edmore = dialogView.findViewById<EditText>(R.id.edmore2)
        val eddes = dialogView.findViewById<EditText>(R.id.eddes2)
        val tvBtn = dialogView.findViewById<Button>(R.id.button7)
        tvBtn.setOnClickListener {
            if (edtitle.text.isEmpty() || edmore.text.isEmpty() || eddes.text.isEmpty())
                Toast.makeText(this, "Fill all fields please", Toast.LENGTH_SHORT).show()
            else {
                var title = edtitle.text.toString()
                var expl = edmore.text.toString()
                var des = eddes.text.toString()
                dbhlr.update("kotlin", s1, s2, s3, title, expl, des)
                Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show()
                myRv()
            }
        }
        dialogBuilder.show()
    }


    fun delete(type:String , s1:String){
        dbhlr.delete(type,s1)
        println("deleted item")
        //retrieve data and update recycler view
        myRv()
    }
    fun myRv(){
        val list = dbhlr.retrive("kotlin")
        myRv.adapter = RecyclerViewAdapter(this,list)
        myRv.layoutManager = LinearLayoutManager(this)
    }
}