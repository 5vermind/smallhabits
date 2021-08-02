package com.h2ve.smallhabits.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.FragmentSettingsBinding
import com.h2ve.smallhabits.util.BaseViewBindingFragment

class SettingsFragment : BaseViewBindingFragment<FragmentSettingsBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.findNavController().navigate(R.id.action_settingsFragment_to_boardFragment)
            }
        })
        if (isAdded) {
            return
        }

    }

    private fun initToolbar() {
        binding.appbarSettings.toolbarBack.colorFilter = PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
        binding.appbarSettings.toolbarBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_settingsFragment_to_boardFragment)
        }
        binding.appbarSettings.toolbarTitle.text = "Settings"
    }
}