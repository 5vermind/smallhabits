package com.h2ve.smallhabits.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.ActivitySignupBinding
import com.h2ve.smallhabits.viewmodel.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SignUpActivity: AppCompatActivity() {

    private val vm: AuthViewModel by viewModel()

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
            val pwConfirm = binding.confirmPw.text.toString()
            val nickname = binding.nickname.text.toString()

            vm.registerUser(uid, upw, pwConfirm, nickname)
        }

    }


}