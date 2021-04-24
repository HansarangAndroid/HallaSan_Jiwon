package org.sopt.soptseminar.gitRepos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptseminar.databinding.ItemInfoBinding
import org.sopt.soptseminar.databinding.ItemRepoBinding
import org.sopt.soptseminar.util.ItemStartDragListener
import org.sopt.soptseminar.util.ItemTouchHelperListener

class RepoInfoAdapter(private val listener : ItemStartDragListener) : RecyclerView.Adapter<RepoInfoAdapter.RepoViewHolder>(), ItemTouchHelperListener {

    val repoList = mutableListOf<RepoInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        //뷰가 새로 생성될 때마다 생성
        //inflate : xml layout을 자바에서 쓸 수 있도록 하는 과정
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding,listener)
    }

    override fun getItemCount(): Int  = repoList.size

    //data 연결하기
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    //개별 뷰(가장 작은 단위)를 사용할 것이라고 알려줌
    class RepoViewHolder(
        private val binding: ItemRepoBinding,
        listener : ItemStartDragListener
    ): RecyclerView.ViewHolder(binding.root){
        init{
            binding.clRepo.setOnLongClickListener {
                listener.onStartDrag(this)
                return@setOnLongClickListener true
            }
        }
        fun onBind(repoInfo: RepoInfo){
            binding.tvName.text = repoInfo.name
            binding.tvLang.text = repoInfo.language
            binding.tvDate.text = repoInfo.date
        }
    }

    override fun onItemMoved(from: Int, to: Int) {
        println("called onItemMoved")
        val fromItem = repoList.removeAt(from)
        repoList.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        repoList.removeAt(position)
        notifyItemRemoved(position)
    }
}