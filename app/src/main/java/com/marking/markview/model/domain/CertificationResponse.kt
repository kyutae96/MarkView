package com.marktong.marktong.model.domain

import com.google.gson.annotations.SerializedName

data class CertificationResponse(
@SerializedName("imp_uid") var impUid : String?,
@SerializedName("merchant_uid") var merchantUid : String?,
@SerializedName("pg_tid") var pgTid : String?,
@SerializedName("pg_provider") var pgProvider : String?,
@SerializedName("name") var name : String?,
@SerializedName("gender") var gender : String?,
@SerializedName("birth") var birth : Int?,
@SerializedName("foreigner") var foreigner : Boolean?,
@SerializedName("phone") var phone : String?,
@SerializedName("carrier") var carrier : String?,
@SerializedName("certified") var certified : Boolean?,
@SerializedName("certified_at") var certifiedAt : Int?,
@SerializedName("unique_key") var uniqueKey : String?,
@SerializedName("unique_in_site") var uniqueInSite : String?,
@SerializedName("origin") var origin : String?
)
