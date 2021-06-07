package org.sopt.soptseminar.gitRepos

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sopt.soptseminar.gitRepos.githubApi.GithubClient

class RepoViewModel(application: Application) : AndroidViewModel(application) {
    val id = MutableLiveData<String>()


}