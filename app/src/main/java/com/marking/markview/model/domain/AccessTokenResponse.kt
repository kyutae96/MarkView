package com.marktong.marktong.model.domain

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
@SerializedName("access_token") var accessToken : String?,
@SerializedName("now") var now : Int?,
@SerializedName("expired_at") var expiredAt : Int?
)
