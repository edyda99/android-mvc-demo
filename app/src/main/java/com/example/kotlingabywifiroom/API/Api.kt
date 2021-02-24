package com.example.kotlingabywifiroom.API

import android.telecom.Call
import androidx.room.ColumnInfo
//import com.example.kotlingabywifiroom.Parent.Items
import com.example.kotlingabywifiroom.Parent.Parentt
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//import com.example.kotlingabywifiroom.Parent.Parent as Pare
//?q=created:>2021-02-17&sort=stars&order=desc&page=0
interface  Api {
    @GET("repositories")
     suspend fun getTop(@Query("q") q: String,
                        @Query("sort") limit: String,
                        @Query("order") order: String,
                        @Query("page") page :String):Parentt
}

object ParentNetwork {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val devbytes :Api = retrofit.create(Api::class.java)

}