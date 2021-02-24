package com.example.kotlingabywifiroom.Room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.kotlingabywifiroom.API.Api
import com.example.kotlingabywifiroom.API.ParentNetwork
import com.example.kotlingabywifiroom.MainActivity
import com.example.kotlingabywifiroom.Parent.Item
//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import com.example.kotlingabywifiroom.Parent.Parentt
import com.example.kotlingabywifiroom.util.Resource
import kotlinx.coroutines.*
import retrofit2.*
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
                emit(Resource.success(ParentNetwork.devbytes.getTop("created:>2021-02-17", "stars", "desc", "${MainActivity.page}")))
            } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occured!"))
        }
    }

}
/*fun getHeroes(): LiveData<Items> {
    val mutableLiveData: MutableLiveData<Items> = MutableLiveData<Items>()
        ParentNetwork.devbytes.getTop("created:>2021-02-17", "stars", "desc", "0")
//                    "created:>2019-11-01", "stars", "desc", "0"
            ?.enqueue(object : Callback<Items> {
                override fun onFailure(call: Call<Items>?, t: Throwable) {
                    Log.d(TAG, "FAILURE$t")
                }

                override fun onResponse(
                    call: Call<Items>?,
                    response: Response<Items>
                ) {
                    Log.d(TAG, "on Success")

                    mutableLiveData.setValue(response.body())


                }

            })



    return mutableLiveData
}*/
//
//    fun insert(parent: Parentt?) {
//        for (p in parent?.items!!) {
//                mpDao?.insert(p)
//            }
//        }
//    suspend fun refreshVideos() {
//            val parents = ParentNetwork.devbytes.getTop("created:>2019-11-01","stars","desc","0")
//            database.mparentDao.insertAll(parents)  }



