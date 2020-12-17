package com.phephen.keytatest.api.service

import com.phephen.keytatest.api.model.DataModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by Fendy Saputro on 16/12/2020.
 * vidis194@gmail.com
 */
interface ApiService {

    @GET("api/v1/get_data/")
    fun getDataName(): Call<List<DataModel>>

    @FormUrlEncoded
    @POST("api/v1/send_data/")
    fun postData(@Field("id") id: Int?, @Field("name") name: String?): Call<ResponseBody?>?
}