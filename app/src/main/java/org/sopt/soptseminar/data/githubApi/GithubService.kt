package org.sopt.soptseminar.data.githubApi

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner:String): Single<List<GithubRepo>>

    @GET("users/{owner}")
    fun getUsers(@Path("owner") owner:String): Single<GithubUser>
}