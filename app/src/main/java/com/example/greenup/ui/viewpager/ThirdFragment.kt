package com.example.greenup.ui.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.FragmentSecondBinding
import com.example.greenup.databinding.FragmentThirdBinding
import com.example.greenup.ui.LoginActivity
import com.example.portplay.base.BaseFragment

class ThirdFragment : BaseFragment<FragmentThirdBinding>(R.layout.fragment_third)  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        binding.skip.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }


}