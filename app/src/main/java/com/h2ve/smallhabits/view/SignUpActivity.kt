package com.h2ve.smallhabits.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.model.SignUpRes
import com.h2ve.smallhabits.databinding.ActivitySignupBinding
import com.h2ve.smallhabits.viewmodel.RegisterViewModel
import com.h2ve.smallhabits.viewmodel.RegisterViewModel.Companion.ID_POLICY
import com.h2ve.smallhabits.viewmodel.RegisterViewModel.Companion.NICKNAME_POLICY
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.regex.Pattern


class SignUpActivity: AppCompatActivity() {

    private val vm: RegisterViewModel by viewModel()

    var signup: SignUpRes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                vm.setError(binding.id, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.id.text.isNullOrEmpty()){
                    if (!vm.isValidId(binding.id, binding.id.text.toString())){
                        vm.setError(binding.id, getString(R.string.sign_up_id_policy))
                    }
                }
            }
        })

        binding.nickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                vm.setError(binding.nickname, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.nickname.text.isNullOrEmpty()){
                    if (!vm.isValidNick(binding.nickname, binding.nickname.text.toString())){
                        vm.setError(binding.nickname, getString(R.string.sign_up_nickname_policy))
                    }
                }
            }
        })

        binding.confirmPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                vm.setError(binding.confirmPw, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.pw.text.isNullOrEmpty() && !binding.confirmPw.text.isNullOrEmpty()){
                    if (binding.confirmPw.text.toString() != binding.pw.text.toString()){
                        vm.setError(binding.confirmPw, getString(R.string.sign_up_mismatch_password))
                    }
                }
            }
        })

        binding.pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                vm.setError(binding.confirmPw, null)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!binding.pw.text.isNullOrEmpty() && !binding.confirmPw.text.isNullOrEmpty()){
                    if (binding.confirmPw.text.toString() != binding.pw.text.toString()){
                        vm.setError(binding.confirmPw, getString(R.string.sign_up_mismatch_password))
                    }
                    if (!vm.isValidPassword(binding.pw, binding.pw.text.toString())){
                        vm.setError(binding.pw, getString(R.string.sign_up_password_policy))
                    }
                }
            }
        })

        binding.signupBtn.setOnClickListener {
            vm.setError(binding.id, null)
            vm.setError(binding.pw, null)
            vm.setError(binding.nickname, null)

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


}