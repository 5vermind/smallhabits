package com.h2ve.smallhabits.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.h2ve.smallhabits.model.Register
import com.h2ve.smallhabits.model.RegisterRes
import com.h2ve.smallhabits.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.util.regex.Pattern

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
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

    fun registerUser(userId:String, password:String, passwordAgain:String, name:String){
        CoroutineScope(Dispatchers.IO).launch {
            val registerResponse = repository.createUser(userId, password, passwordAgain, name)
            if(registerResponse.isSuccessful){
                Log.d("LKJLKJLKJLKJ", "registerUser: ${registerResponse.body()?.userId}")
                //성공시 로직
                //로그인 자동으로 해주고 토큰 저장하고 메인화면까지
            }
        }
    }

    fun loginUser(userId:String, password:String){
        CoroutineScope(Dispatchers.IO).launch {
            val loginResponse = repository.getUser(userId, password)
            if(loginResponse.isSuccessful){
                Log.d("LKJLKJLKJLKJ", "registerUser: ${loginResponse.body()?.token}")
                //토큰 저장하고 메인 화면으로
            }
        }
    }
}