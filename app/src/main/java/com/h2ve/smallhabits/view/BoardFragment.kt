package com.h2ve.smallhabits.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.FragmentBoardBinding
import com.h2ve.smallhabits.util.BaseViewBindingFragment
import com.h2ve.smallhabits.view.adapter.HabitGridListAdapter

class BoardFragment : BaseViewBindingFragment<FragmentBoardBinding>() {

    private var habitGridListAdapter = HabitGridListAdapter()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBoardBinding {
        return FragmentBoardBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initGridListAdapter()
    }

    private fun initToolbar() {
        binding.appbar.ivAppbarSettings.setOnClickListener {
            //navigate to settings fragment
            it.findNavController().navigate(R.id.action_boardFragment_to_settingsFragment)
        }
    }

    private fun initGridListAdapter() {
        binding.rvCard.layoutManager = GridLayoutManager(activity, 2)
        binding.rvCard.adapter = habitGridListAdapter

    }

}