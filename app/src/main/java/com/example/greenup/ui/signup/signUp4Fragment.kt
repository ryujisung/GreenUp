package com.example.greenup.ui.signup

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.greenup.R
import com.example.greenup.databinding.FragmentSignUp4Binding
import com.example.greenup.ui.signup.SignUpFragment
import com.example.greenup.ui.signup.SignUpViewModel

class signUp4Fragment : SignUpFragment<FragmentSignUp4Binding>(R.layout.fragment_sign_up4) {
    val viewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.hdSignUp3.setNavigationClickListener {
            gotoPrev()
        }

        binding.next.setOnClickListener {
            hideKeyboard()
            if (viewModel.inputCheckNameNickName()) {


                if(viewModel.sigininAndSignup()) {
                    gotoNext()
                }
            }
            else{
                YoYo.with(Techniques.Shake)
                    .duration(500)
                    .repeat(0)
                    .playOn(binding.txtSign3Eror)
                binding.edtSignUp3Email.backgroundTintList = ColorStateList.valueOf(Color.rgb(250,49,49))
                binding.txtSign3Eror.visibility = View.VISIBLE
            }
        }
    }
    override val currentPage: Int = 4
}