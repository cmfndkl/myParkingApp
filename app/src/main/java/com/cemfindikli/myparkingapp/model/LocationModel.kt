package com.cemfindikli.myparkingapp.model

import com.google.gson.annotations.SerializedName


data class LocationModel(
        @SerializedName("name")
        val name:String,
        @SerializedName("image")
        val image:String,
        @SerializedName("locationsx")
        val locationsx:String,
        @SerializedName("locationsy")
        val locationsy:String
)