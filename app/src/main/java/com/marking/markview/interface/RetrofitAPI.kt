package com.marking.markview.`interface`

import com.marking.markview.model.SignUpUserModel
import com.marking.markview.model.UserModel
import retrofit2.Call
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("api/auth/login")
        fun getLoginInfo() : Call<UserModel>

    @POST("api/auth/signup")
    fun getSignInfo() : Call<SignUpUserModel>
}