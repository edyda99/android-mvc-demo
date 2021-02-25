package com.example.kotlingabywifiroom.parentt

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity
data class Item(
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("private")
    val pri: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("owner")
    val owner : Owner
)