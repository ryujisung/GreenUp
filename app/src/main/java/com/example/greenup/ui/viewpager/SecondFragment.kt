package com.example.greenup.ui.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.FragmentFirstBinding
import com.example.greenup.databinding.FragmentSecondBinding
import com.example.greenup.ui.LoginActivity
import com.example.portplay.base.BaseFragment

class SecondFragment : BaseFragment<FragmentSecondBinding>(R.layout.fragment_second) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        binding.skip.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}