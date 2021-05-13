package org.sopt.soptseminar.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.soptseminar.R
import org.sopt.soptseminar.gitRepos.githubApi.GithubClient
import org.sopt.soptseminar.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel : LoginViewModel by viewModels() //위임초기화

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        login()
        signUp()
        getSafeArgs()
    }

    private fun login(){
        binding.btnLogin.setOnClickListener{
            if(viewModel.checkInputText()){
                toast("아이디, 패스워드를 모두 입력해주세요");
            }
            else{
                viewModel.doLogin()
                viewModel.loginSuccess.observe(viewLifecycleOwner){success ->
                    Log.d("Test","loginFragment > login"+success)
                    if(success){
                        toast("Login Success! Id : "+binding.etId.text.toString())
                        val bundle = Bundle()
                        bundle.putString("ID",binding.etId.text.toString())
                        Navigation.findNavController(binding.root).navigate(R.id.action_fragment_login_to_fragment_home,bundle)
                    }
                    else{
                        toast("Please check email or password")
                    }
                }
            }
            /*    //TODO : repository 문제 해결하면 주석 풀기
            else if(viewModel.isLogin(binding.etId.text.toString(), binding.etPassword.text.toString()) == 1){
                toast("아이디 혹은 패스워드가 올바르지 않습니다.")
            }
            else if(viewModel.isLogin(binding.etId.text.toString(), binding.etPassword.text.toString()) == 2){
                toast("존재하지 않는 아이디입니다.")
            }
            else{
                toast("로그인 성공")
                val bundle = Bundle()
                bundle.putString("ID",binding.etId.text.toString())
                Navigation.findNavController(binding.root).navigate(R.id.action_fragment_login_to_fragment_home,bundle)
            }*/
        }
    }

    private fun signUp(){
        binding.tvSignup.setOnClickListener{
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }

    private fun toast(string : String){
        Toast.makeText(requireContext(),string, Toast.LENGTH_SHORT).show()
    }
}