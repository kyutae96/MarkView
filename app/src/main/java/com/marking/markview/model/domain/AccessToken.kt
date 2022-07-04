package com.marktong.marktong.model.domain

import com.google.gson.annotations.SerializedName

data class AccessToken(
@SerializedName("code") var code : Int?,
@SerializedName("message") var message : Any?,
@SerializedName("response") var response : AccessTokenResponse?
)
