package com.example.kotlingabywifiroom.API

//import com.example.kotlingabywifiroom.Parent.Items
import com.example.kotlingabywifiroom.parentt.Parentt
import com.google.android.gms.ads.internal.gmsg.HttpClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.okhttp.internal.http.OkHeaders
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//import com.example.kotlingabywifiroom.Parent.Parent as Pare
//?q=created:>2021-02-17&sort=stars&order=desc&page=0
interface  Api {
    @GET("repositories")
     suspend fun getTop(@Query("q") q: String,
                        @Query("sort") limit: String,
                        @Query("order") order: String,
                        @Query("page") page :String): Parentt
}

object ParentNetwork {
    const val FIRST_PAGE = 0
    const val POST_PER_PAGE = 20
    val client : OkHttpClient = OkHttpClient()
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    val devbytes :Api = retrofit.create(Api::class.java)

}