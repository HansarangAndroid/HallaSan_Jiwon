package org.sopt.soptseminar.home

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.soptseminar.data.githubApi.GithubClient

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val id = MutableLiveData<String>()


}