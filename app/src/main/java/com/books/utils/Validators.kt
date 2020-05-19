package com.hangarbot

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class Validators {

    companion object {
        var errorMessage: String? = ""
    }


    private fun isValidEmailId(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun validateEmail(email: String): Boolean = if (TextUtils.isEmpty(email)) {
        errorMessage = ""
        false
    } else if (!isValidEmailId(email)) {
        errorMessage = ""
        false
    } else {
        true
    }

    fun validateLoginData(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            errorMessage = "Please enter email and password."
            return false
        } else if (TextUtils.isEmpty(email)) {
            errorMessage = "Please enter email."
            return false
        } else if (!isValidEmailId(email)) {
            errorMessage = "Please enter a valid email id."
            return false
        } else if (TextUtils.isEmpty(password)) {
            errorMessage = "Please enter password."
            return false
        } else if (password.length < 6) {
            errorMessage = "Please enter a valid password"
            return false
        } else {
            return true
        }
    }

}
