package com.marktong.marktong.model.domain

import com.google.gson.annotations.SerializedName

data class AuthData(
@SerializedName("imp_key") var api_key : String? = null,
@SerializedName("imp_secret") var api_secret : String? = null
)
