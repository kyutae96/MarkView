package com.marking.markview.model.SignUp.Unique

import com.marking.markview.model.SignUp.Certification.MetaModel
import com.marking.markview.model.SignUp.Certification.StatusModel


class SignUpUniqueModel(
    val unique_key: String? = null
)
class CheckDuplicateModel(
    val user_id: String? = null
)
class ResponseModel(
    val meta: MetaModel,
    val status: StatusModel,
    val data: String? = null
)
