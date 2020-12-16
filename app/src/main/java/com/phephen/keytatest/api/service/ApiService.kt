package com.phephen.keytatest.api.service

import com.phephen.keytatest.api.model.DataModel
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Fendy Saputro on 16/12/2020.
 * vidis194@gmail.com
 */
interface ApiService {

    @GET("api/v1/get_data/")
    fun getDataName(): Call<List<DataModel>>
}