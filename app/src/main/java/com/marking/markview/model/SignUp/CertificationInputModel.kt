package com.marking.markview.model.SignUp.Certification

class CertificationInputModel(
    val imp_uid: String? = null

)

class CertificationResponseModel(
    val meta: MetaModel,
    val status: StatusModel,
    val data: CertificationModel
) {
    data class CertificationModel(
        var name: String? = null,
        var birthday: String? = null,
        var gender: String? = null,
        var phone: String? = null,
        var unique_key: String? = null
    )

}

