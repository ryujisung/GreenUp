package com.example.greenup.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.greenup.R
import com.example.greenup.databinding.FragmentSignUp5Binding
import com.example.greenup.ui.signup.SignUpActivity
import com.example.greenup.ui.signup.SignUpFragment
import com.example.greenup.ui.signup.SignUpViewModel

class signUp5Fragment : SignUpFragment<FragmentSignUp5Binding>(R.layout.fragment_sign_up5) {
    val viewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener ((activity as SignUpActivity).gotoMain)
    }
    override val currentPage: Int = 5
}