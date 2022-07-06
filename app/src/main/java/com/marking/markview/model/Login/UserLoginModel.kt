package com.marking.markview.model.Login

import com.marking.markview.model.SignUp.Certification.MetaModel
import com.marking.markview.model.SignUp.Certification.StatusModel

data class LoginModel(
    var user_id: String? = null,
    var password: String? = null,
    var login_ip: String? = null
)

class LoginResponseModel(
    val meta: MetaModel,
    val status: StatusModel,
    val data: LoginDataModel
) {
    data class LoginDataModel(
        var access_token: String? = null,
        var refresh_token: String? = null,
        var token_type: String? = null
    )

}
