package com.cemfindikli.myparkingapp.service

import com.cemfindikli.myparkingapp.model.LocationModel
import io.reactivex.Observable
import retrofit2.http.GET

interface LocationAPI {



    @GET("cmfndkl/mockjson/main/location.json")
    fun getData(): Observable<List<LocationModel>>




}