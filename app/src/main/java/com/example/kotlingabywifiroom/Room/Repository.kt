package com.example.kotlingabywifiroom.Room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.kotlingabywifiroom.API.Api
import com.example.kotlingabywifiroom.API.ParentNetwork
import com.example.kotlingabywifiroom.MainActivity
import com.example.kotlingabywifiroom.parentt.Item
//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import com.example.kotlingabywifiroom.parentt.Parentt
import com.example.kotlingabywifiroom.util.Resource
import kotlinx.coroutines.*
import java.lang.Exception

//private val database: ParentDatabase
class Repository() {
    //    private var mpDao: PDao? = null
    private var mAllParents: LiveData<List<Item?>?>? = null
    private val TAG: String = Repository::class.java.getSimpleName()


    val pApi: Api? = null
//    val parents: LiveData<List<Item>>? = mpDao?.getParents()

//    init{getHeroes()}
//"created:>2021-02-17", "stars", "desc", "0"


    fun getHeroes(): LiveData<Resource<Parentt>> = liveData(Dispatchers.IO) {
        Log.d(TAG, "taking results")
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    ParentNetwork.devbytes.getTop(
                        "created:>2021-02-17",
                        "stars",
                        "desc",
                        "${MainActivity.page}"
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occured!"))
        }
    }

}
