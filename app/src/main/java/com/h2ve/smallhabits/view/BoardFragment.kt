package com.h2ve.smallhabits.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.FragmentBoardBinding
import com.h2ve.smallhabits.util.BaseViewBindingFragment

class BoardFragment : BaseViewBindingFragment<FragmentBoardBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBoardBinding {
        return FragmentBoardBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        binding.appbar.ivAppbarSettings.setOnClickListener {
            //navigate to settings fragment
            it.findNavController().navigate(R.id.action_boardFragment_to_settingsFragment)
        }
    }

}