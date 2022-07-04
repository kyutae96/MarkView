package com.marking.markview.model



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
