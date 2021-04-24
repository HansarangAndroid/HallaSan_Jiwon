package org.sopt.soptseminar.gitRepos

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.soptseminar.data.githubApi.GithubClient
import org.sopt.soptseminar.databinding.FragmentRepositoryBinding
import org.sopt.soptseminar.home.UserInfo
import org.sopt.soptseminar.home.UserInfoAdapter
import org.sopt.soptseminar.util.ItemStartDragListener
import org.sopt.soptseminar.util.ItemTouchHelperCallback

class RepoFragment : Fragment() , ItemStartDragListener {
    private var _binding:FragmentRepositoryBinding?=null
    private val binding get() = _binding?:error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel : RepoViewModel by viewModels()
    private lateinit var repoAdapter: RepoInfoAdapter
    private lateinit var mLayoutManager:RecyclerView.LayoutManager

    private var repoInfoList = mutableListOf<RepoInfo>()

    private lateinit var itemTouchHelper : ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoAdapter = RepoInfoAdapter(this)
        binding.rcRepoInfo.adapter = repoAdapter

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(repoAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rcRepoInfo)

        //Home -> Repo로 이동
        getSafeArgs()
        getRepoGit(viewModel.id.value.toString())

        binding.ivLinear.setOnClickListener{
            //linear
            mLayoutManager = LinearLayoutManager(context)
            binding.rcRepoInfo.layoutManager = mLayoutManager
        }
        binding.ivGrid.setOnClickListener{
            //grid
            mLayoutManager = GridLayoutManager(context,2)
            binding.rcRepoInfo.layoutManager = mLayoutManager
        }

    }

    @SuppressLint("CheckResult")
    fun getRepoGit(owner:String)  {
        GithubClient.getApi().getRepos(owner)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({items ->
                repoInfoList.clear()
                items.forEach{
                    repoInfoList.add(RepoInfo(it.name,it.language,it.date_created,it.url))
                    println(it)
                }
                repoAdapter.repoList.clear() //초기화
                repoAdapter.repoList.addAll(repoInfoList)
                repoAdapter.notifyDataSetChanged()
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