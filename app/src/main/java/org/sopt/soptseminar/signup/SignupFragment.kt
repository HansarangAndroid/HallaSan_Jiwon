package org.sopt.soptseminar.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.FragmentLoginBinding
import org.sopt.soptseminar.databinding.FragmentSignupBinding

class SignupFragment : Fragment(){
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel : SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        //viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
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
        binding.btnSignup.setOnClickListener {
            if(viewModel.checkInputText()){
                Toast.makeText(requireContext(), "모든 정보를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle = Bundle()
                bundle.putString("NAME",binding.etName.text.toString())
                bundle.putString("ID",binding.etId.text.toString())
                bundle.putString("PASSWORD",binding.etPassword.text.toString())

                Navigation.findNavController(binding.root).navigate(R.id.passArgs_signup_to_login,bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }
}
