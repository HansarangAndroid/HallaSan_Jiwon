package org.sopt.soptseminar.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.soptseminar.R
import org.sopt.soptseminar.gitRepos.githubApi.GithubClient
import org.sopt.soptseminar.databinding.FragmentHomeBinding
import org.sopt.soptseminar.util.ItemStartDragListener
import org.sopt.soptseminar.util.ItemTouchHelperCallback

class HomeFragment : Fragment(), ItemStartDragListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel : HomeViewModel by viewModels() //위임초기화
    private lateinit var userInfoAdapter: UserInfoAdapter
    private lateinit var mLayoutManager:RecyclerView.LayoutManager

    private var userInfoList = mutableListOf<UserInfo>()

    private lateinit var itemTouchHelper : ItemTouchHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //TODO
        //어댑터 설정
        userInfoAdapter = UserInfoAdapter(this)
        binding.rcUserInfo.adapter = userInfoAdapter

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(userInfoAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rcUserInfo)

        //Login -> Home으로 ID 이동
        getSafeArgs()
        //초기 설정
        getUserGit(viewModel.id.value.toString())

        //버튼 클릭시
        binding.btnSearch.setOnClickListener {
            //github API
            getUserGit(binding.etSearchId.text.toString())
        }
        binding.ivLinear.setOnClickListener{
            //linear
            mLayoutManager = LinearLayoutManager(context)
            binding.rcUserInfo.layoutManager = mLayoutManager
        }
        binding.ivGrid.setOnClickListener{
            //grid
            mLayoutManager = GridLayoutManager(context,2)
            binding.rcUserInfo.layoutManager = mLayoutManager
        }
        binding.tvRepo.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("ID",binding.etSearchId.text.toString())
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_home_to_fragment_repository,bundle)
        }
    }

    @SuppressLint("CheckResult")
    fun getUserGit(userId: String)  {
        GithubClient.getApi.getUsers(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({items ->
                //User 정보
                userInfoList.clear() //초기화
                userInfoList.add(UserInfo("ID",items.id))
                userInfoList.add(UserInfo("url",items.url))
                userInfoList.add(UserInfo("date_created",items.date_created))
                userInfoList.add(UserInfo("date_update",items.date_update))
                userInfoList.add(UserInfo("followers",items.followers.toString()))
                userInfoList.add(UserInfo("following",items.following.toString()))

                userInfoAdapter.userList.clear() //초기화
                userInfoAdapter.userList.addAll(userInfoList)
                userInfoAdapter.notifyDataSetChanged()
                println(items)
            },{e ->
                println(e.toString())
            })
    }

    private fun getSafeArgs(){
        arguments?.let{
            viewModel.id.value = it.getString("ID").toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}
