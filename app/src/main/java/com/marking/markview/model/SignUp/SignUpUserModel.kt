package com.marking.markview.model

import com.marking.markview.model.SignUp.Certification.MetaModel
import com.marking.markview.model.SignUp.Certification.StatusModel


data class SignUpUserModel(
    var user_id: String? = null,
    var name: String? = null,
    var password: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    var phone: String? = null,
    var gender: String? = null,
    var unique_key: String? = null,
    var department: String? = null,
    var zip_code: String? = null,
    var address: String? = null
)
class SignUpResponseModel(
    val meta: MetaModel,
    val status: StatusModel,
    val data: SignUpDataModel
) {
    data class SignUpDataModel(
        var user_id: String? = null,
        var name: String? = null,
        var birthday: String? = null,
        var gender: String? = null,
        var email: String? = null,
        var phone: String? = null,
        var department: String? = null,
        var zip_code: String? = null,
        var address: String? = null,
        var login_ip: String? = null,
        var login_count: Int? = null,
        var id: Int? = null,
        var is_active: Boolean? = null
    )

}