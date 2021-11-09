package com.example.advancedstudyapp.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.advancedstudyapp.dataModel.Note


class DBHlr2(context: Context?) : SQLiteOpenHelper(context, "details.db", null, 1) {
    val sql : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL("create table kotlin(Title text, Des text, More text)")
            p0.execSQL("create table android(Title text, Des text, More text)")

        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun savedata(type: String, s1: String, s2:String, s3:String){
        val cv = ContentValues()
        cv.put("Title", s1)
        cv.put("More", s2)
        cv.put("Des", s3)
        if(type=="android"){
            sql.insert("android", null, cv)
        }else {
            sql.insert("kotlin", null, cv)
        }
    }

    @SuppressLint("Range")
    fun retrive(type:String) :ArrayList<Note> {
        val list = ArrayList<Note>()
        if(type=="kotlin") {
            val cursor: Cursor = sql.rawQuery("select * from kotlin", null)
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    val title: String = cursor.getString(cursor.getColumnIndex("Title"))
                    val des: String = cursor.getString(cursor.getColumnIndex("Des"))
                    val explain: String = cursor.getString(cursor.getColumnIndex("More"))
                    list.add(Note("kotlin", title, explain, des))
                    cursor.moveToNext()
                }
            }
        }else{
            val cursor: Cursor = sql.rawQuery("select * from android", null)
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    val title: String = cursor.getString(cursor.getColumnIndex("Title"))
                    val des: String = cursor.getString(cursor.getColumnIndex("Des"))
                    val explain: String = cursor.getString(cursor.getColumnIndex("More"))
                    list.add(Note("android",title,explain,des))
                    cursor.moveToNext()
                }
            }
        }
        return list
    }

    fun update(type:String ,s1 :String ,s2 :String, s3 :String, news1 :String,news2 :String,news3 :String) {
        val cv = ContentValues()
        if (type == "android") {
            cv.put("Title", news1)
            cv.put("More", news2)
            cv.put("Des", news3)
            sql.update("android", cv, "Title=?", arrayOf(s1))
            sql.update("android", cv, "More=?", arrayOf(s2))
            sql.update("android", cv, "Des=?", arrayOf(s3))
        } else {
            cv.put("Title", news1)
            cv.put("More", news2)
            cv.put("Des", news3)
            sql.update("kotlin", cv, "Title=?", arrayOf(s1))
            sql.update("kotlin", cv, "More=?", arrayOf(s2))
            sql.update("kotlin", cv, "Des=?", arrayOf(s3))
        }
    }
    fun delete(type:String, s1 :String){
        if(type=="android") {
            sql.delete("android", "Title=?", arrayOf(s1))
        }else{
            sql.delete("kotlin", "Title=?", arrayOf(s1))
        }
    }
}
