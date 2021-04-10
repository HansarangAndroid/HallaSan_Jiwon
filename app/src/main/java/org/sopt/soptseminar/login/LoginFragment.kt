package org.sopt.soptseminar.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //TODO
        login()
        signUp()
        getSafeArgs()
    }

    private fun login(){
        binding.buttonLogin.setOnClickListener{
            if(viewModel.checkInputText()){
                Toast.makeText(requireContext(),"아이디, 패스워드를 올바르게 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),"로그인 성공", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.root).navigate(R.id.action_fragment_login_to_fragment_home)
            }
        }
    }

    private fun signUp(){
        binding.textViewSignup.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_login_to_fragment_signup)
        }
    }

    //fragment간 정보 전달 (Bundle이용)
    private fun getSafeArgs(){
        arguments?.let{
            //viewModel.name.value = it.getString("NAME").toString()
            viewModel.id.value = it.getString("ID").toString()
            viewModel.password.value = it.getString("PASSWORD").toString()
        }
    }
}