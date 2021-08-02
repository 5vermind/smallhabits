package com.h2ve.smallhabits.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.FragmentChainBinding
import com.h2ve.smallhabits.util.BaseViewBindingFragment

class ChainFragment : BaseViewBindingFragment<FragmentChainBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChainBinding {
        return FragmentChainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        binding.appbar.ivAppbarSettings.setOnClickListener {
            //navigate to settings fragment
            it.findNavController().navigate(R.id.action_chainFragment_to_settingsFragment)
        }
    }

}