package com.example.kotlingabywifiroom.Room

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlingabywifiroom.Parent.Item
//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import com.example.kotlingabywifiroom.ParentViewModel.ParentViewModel
//
//@Dao
//interface PDao {
//    @Query("select * from Item")
//    fun getParents(): LiveData<List<Item>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert( parents : Item)
//}

//@Database(entities = [Item::class], version = 1,exportSchema = false)
//abstract class ParentDatabase: RoomDatabase() {
////    abstract val mparentDao: PDao
//}
//
//private lateinit var INSTANCE: ParentDatabase
//
//fun getDatabase(context: Context): ParentDatabase {
//    synchronized(ParentDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(context.applicationContext,
//                ParentDatabase::class.java,
//                "Parents").build()
//            Log.d(TAG,"ANA BL GETDATABASE")
//        }
//    }
//    return INSTANCE
//}
