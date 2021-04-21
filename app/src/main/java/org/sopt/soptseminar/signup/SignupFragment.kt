package org.sopt.soptseminar.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.FragmentSignupBinding

class SignupFragment : Fragment(){
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel : SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //TODO
        signup()
    }

    private fun signup(){
        binding.buttonSignup.setOnClickListener {
            if(viewModel.checkInputText()){
                Toast.makeText(requireContext(), "모든 정보를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle = Bundle()
                bundle.putString("NAME",binding.EditTextName.text.toString())
                bundle.putString("ID",binding.EditTextId.text.toString())
                bundle.putString("PASSWORD",binding.EditTextPassword.text.toString())

                Navigation.findNavController(binding.root).navigate(R.id.passArgs_signup_to_login,bundle)
            }
        }
    }
}
