package com.example.advancedstudyapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.advancedstudyapp.dataBase.DBHlr2

class MyViewModel (applicationContext : Application): AndroidViewModel(applicationContext)  {
    lateinit var dbhlr : DBHlr2
    init {
        dbhlr = DBHlr2(applicationContext)
    }
}