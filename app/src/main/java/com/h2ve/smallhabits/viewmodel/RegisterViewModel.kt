package com.h2ve.smallhabits.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    companion object {
        const val ID_POLICY: String = "4~12자리의 대소문자/숫자만 가능합니다."
        const val NICKNAME_POLICY: String = "2~10자리의 한글/대소문자/숫자만 가능합니다."
        const val PASSWORD_POLICY: String = "8~30자리의 숫자/대문자/소문자/특수문자(!,_) 중 2가지 이상의 조합이어야 합니다."
        const val MATCH_PASSWORD: String = "비밀번호와 동일하지 않습니다."
    }

    fun isValidPassword(data: Any, str: String): Boolean {

        var valid = true

        val lenReg = "^[a-zA-Z0-9!_]{8,30}\$"
        val lowerReg = "[a-z]"
        val upperReg = "[A-Z]"
        val numReg = "[0-9]"
        val specReg = "[!_]"

        var cnt = 0
        if (Pattern.matches(lenReg, str)) {
            if (Pattern.compile(lowerReg).matcher(str).find()) {
                cnt++
            }
            if (Pattern.compile(upperReg).matcher(str).find()) {
                cnt++
            }
            if (Pattern.compile(numReg).matcher(str).find()) {
                cnt++
            }
            if (Pattern.compile(specReg).matcher(str).find()) {
                cnt++
            }

            if (cnt < 2) {
                valid = false
            }
        } else valid = false

        if (!valid) {
            setError(data, PASSWORD_POLICY)
        }

        return valid
    }

    fun isValidId(data: Any, str: String): Boolean {

        var valid = true

        val exp = "^[a-zA-z0-9]{4,12}\$"

        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        if (!valid) {
            setError(data, ID_POLICY)
        }

        return valid
    }

    fun isValidNick(data: Any, str: String): Boolean {

        var valid = true

        val exp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,10}\$"

        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        if (!valid) {
            setError(data, NICKNAME_POLICY)
        }

        return valid
    }

    fun setError(data: Any, error: String?) {
        if (data is TextInputEditText) {
            if (data.parent.parent is TextInputLayout) {
                (data.parent.parent as TextInputLayout).error = error
            } else {
                data.error = error
            }
        }
    }
}