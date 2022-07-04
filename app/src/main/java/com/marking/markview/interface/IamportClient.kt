package com.marking.markview.`interface`

import retrofit2.Call
import retrofit2.http.*

public interface IamportClient {

    @POST("/users/getToken")
    fun token(@Body auth : AuthData) : Call<AccessToken>

    @GET("/certifications/{imp_uid}")
    fun certification_by_imp_uid(
    @Header("Authorization") token : String,
    @Path("imp_uid") imp_uid : String
    ) :Call <Certification>
}

