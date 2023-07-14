package com.example.greenup.ui.signup

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.greenup.R
import com.example.greenup.databinding.FragmentSignUp1Binding
import com.example.greenup.ui.signup.SignUpFragment
import com.example.greenup.ui.signup.SignUpViewModel


class signUp1Fragment : SignUpFragment<FragmentSignUp1Binding>(R.layout.fragment_sign_up1) {
    val viewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.hdSignUp1.setNavigationClickListener {
            requireActivity().finish()
        }

        binding.next.setOnClickListener {
            hideKeyboard()
            Log.e("dd",viewModel.inputCheckEmailPassword().toString())
            if (viewModel.inputEmailCheck()) {
                viewModel.errorMessage.value = null
                gotoNext()
            }
            else{
                YoYo.with(Techniques.Shake)
                    .duration(500)
                    .repeat(0)
                    .playOn(binding.txtSign1Eror)
                binding.edtSignUp1Email.backgroundTintList = ColorStateList.valueOf(Color.rgb(250,49,49))
                binding.txtSign1Eror.visibility = View.VISIBLE
            }
        }
    }

    override val currentPage: Int = 1
}