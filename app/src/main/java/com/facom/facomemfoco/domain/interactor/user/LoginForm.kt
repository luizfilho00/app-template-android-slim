package com.facom.facomemfoco.domain.interactor.user

class LoginForm {
    var username: String? = null
    var password: String? = null

    fun useForm(action: (username: String, password: String) -> Unit): InvalidFieldsException? {
        username?.let { username ->
            password?.let { password ->
                action.invoke(username, password)
                return null
            }
        }
        return InvalidFieldsException().apply {
            if (username == null) addField(InvalidFieldsException.USERNAME)
            if (password == null) addField(InvalidFieldsException.PASSWORD)
        }

    }
}