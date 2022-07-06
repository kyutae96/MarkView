package com.marking.markview.`interface`

import com.marking.markview.model.*
import com.marking.markview.model.Login.LoginModel
import com.marking.markview.model.Login.LoginResponseModel
import com.marking.markview.model.SignUp.Certification.CertificationInputModel
import com.marking.markview.model.SignUp.Certification.CertificationResponseModel
import com.marking.markview.model.SignUp.Unique.CheckDuplicateModel
import com.marking.markview.model.SignUp.Unique.ResponseModel
import com.marking.markview.model.SignUp.Unique.SignUpUniqueModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitAPI {
    @Headers("accept: application/json","content-type: application/json")
    @POST("api/auth/certification")
    fun getCertification( //danal uid 가져오기
        @Body jsonparms: CertificationInputModel
    ) : Call<CertificationResponseModel>

    @Headers("accept: application/json","content-type: application/json")
    @POST("api/auth/check/sign-up")
    fun getUserData(  //가입여부 확인
        @Body jsonparams: SignUpUniqueModel
    ) : Call<ResponseModel>

    @Headers("accept: application/json","content-type: application/json")
    @POST("api/auth/check/id-duplicate")
    fun Duplicate(  //아이디 중복체크
        @Body jsonparams: CheckDuplicateModel
    ) : Call<ResponseModel>

    @Headers("accept: application/json","content-type: application/json")
    @POST("api/auth/signup")
    fun getSignInfo(
        @Body jsonparams: SignUpUserModel
    ) : Call<SignUpResponseModel>

    @Headers("accept: application/json","content-type: application/json")
    @POST("api/auth/login")
    fun getLoginInfo(
        @Body jsonparams: LoginModel
    ) : Call<LoginResponseModel>
}