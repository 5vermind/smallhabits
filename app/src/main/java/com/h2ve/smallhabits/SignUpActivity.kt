package com.h2ve.smallhabits

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.h2ve.smallhabits.databinding.ActivitySignupBinding
import java.util.regex.Pattern


class SignUpActivity: AppCompatActivity() {

    var signup:SignUpRes? = null
    private val ID_POLICY:String = "4~12자리의 대소문자/숫자만 가능합니다."
    private val NICKNAME_POLICY:String = "2~10자리의 한글/대소문자/숫자만 가능합니다."
    private val PASSWORD_POLICY:String = "8~30자리의 숫자/대문자/소문자/특수문자(!,_) 중 2가지 이상의 조합이어야 합니다."
    private val MATCH_PASSWORD:String = "비밀번호와 동일하지 않습니다."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val api = ApiService.create()

        binding.id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setError(binding.id, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.id.text.isNullOrEmpty()){
                    if (!isValidId(binding.id, binding.id.text.toString())){
                        setError(binding.id, ID_POLICY)
                    }
                }
            }
        })

        binding.nickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setError(binding.nickname, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.nickname.text.isNullOrEmpty()){
                    if (!isValidNick(binding.nickname, binding.nickname.text.toString())){
                        setError(binding.nickname, NICKNAME_POLICY)
                    }
                }
            }
        })

        binding.confirmPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setError(binding.confirmPw, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.pw.text.isNullOrEmpty() && !binding.confirmPw.text.isNullOrEmpty()){
                    if (binding.confirmPw.text.toString() != binding.pw.text.toString()){
                        setError(binding.confirmPw, MATCH_PASSWORD)
                    }
                }
            }
        })

        binding.pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setError(binding.confirmPw, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.pw.text.isNullOrEmpty() && !binding.confirmPw.text.isNullOrEmpty()){
                    if (binding.confirmPw.text.toString() != binding.pw.text.toString()){
                        setError(binding.confirmPw, MATCH_PASSWORD)
                    }
                    if (!isValidPassword(binding.pw, binding.pw.text.toString())){
                        setError(binding.pw, PASSWORD_POLICY)
                    }
                }
            }
        })

        binding.signupBtn.setOnClickListener {
            setError(binding.id, null)
            setError(binding.pw, null)
            setError(binding.nickname, null)

            val uid = binding.id.text.toString()
            val upw = binding.pw.text.toString()
            val nickname = binding.nickname.text.toString()

            Intent(this@SignUpActivity, BoardActivity::class.java).apply {
                startActivity(this)
            }

            /*
            if(isValidId(binding.id, uid) && isValidPassword(binding.pw, upw) && isValidNick(binding.nickname, nickname) && (binding.confirmPw.text.toString() == binding.pw.text.toString())) {
                api.reqSignUp(uid, upw, nickname).enqueue(object : Callback<SignUpRes> {
                    override fun onFailure(call: Call<SignUpRes>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "Failed connection", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<SignUpRes>, response: Response<SignUpRes>) {
                        signup = response.body()
                        if (signup?.success == null) {
                            Toast.makeText(this@SignUpActivity, "sign up failed", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@SignUpActivity, "success: " + signup?.success.toString() +
                                    "\nresult code: " + signup?.resultCode, Toast.LENGTH_SHORT).show()
                            MySharedPreferences.setUserId(this@SignUpActivity, uid)
                            MySharedPreferences.setUserPass(this@SignUpActivity, upw)
                            MySharedPreferences.setUserNick(this@SignUpActivity, nickname)
                            Intent(this@SignUpActivity, BoardActivity::class.java).apply {
                                startActivity(this)
                            }
                        }
                    }
                })
            }
             */
        }

    }

    private fun isValidPassword(data: Any, str: String): Boolean {

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

    private fun isValidId(data: Any, str: String): Boolean {

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

    private fun isValidNick(data: Any, str: String): Boolean {

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

    private fun setError(data: Any, error: String?) {
        if (data is TextInputEditText) {
            if (data.parent.parent is TextInputLayout) {
                (data.parent.parent as TextInputLayout).error = error
            } else {
                data.error = error
            }
        }
    }

}