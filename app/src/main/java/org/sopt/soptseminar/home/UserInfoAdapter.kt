package org.sopt.soptseminar.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptseminar.databinding.ItemInfoBinding
import org.sopt.soptseminar.util.ItemStartDragListener
import org.sopt.soptseminar.util.ItemTouchHelperListener

class UserInfoAdapter(private val listener : ItemStartDragListener) : RecyclerView.Adapter<UserInfoAdapter.UserViewHolder>(), ItemTouchHelperListener {

    val userList = mutableListOf<UserInfo>()

    //아이템마다 viewholder를 만드는 방법 정의하기 (layoutmanager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //뷰가 새로 생성될 때마다 생성
        //inflate : xml layout을 자바에서 쓸 수 있도록 하는 과정
        val binding = ItemInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding,listener)
    }

    override fun getItemCount(): Int  = userList.size

    //data 연결하기
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    //개별 뷰(가장 작은 단위)를 사용할 것이라고 알려줌
    class UserViewHolder(
        private val binding: ItemInfoBinding,
        listener : ItemStartDragListener
    ): RecyclerView.ViewHolder(binding.root){
        init{
            binding.clUser.setOnLongClickListener {
                listener.onStartDrag(this)
                return@setOnLongClickListener true
            }
        }
        fun onBind(userInfo: UserInfo){
            binding.tvTitle.text = userInfo.title
            binding.tvContent.text = userInfo.content
        }
    }

    override fun onItemMoved(from: Int, to: Int) {
        println("called onItemMoved")
        val fromItem = userList.removeAt(from)
        userList.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

}