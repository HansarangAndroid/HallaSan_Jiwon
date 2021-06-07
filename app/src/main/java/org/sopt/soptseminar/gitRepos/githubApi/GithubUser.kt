package org.sopt.soptseminar.gitRepos.githubApi

import com.google.gson.annotations.SerializedName

data class GithubUser (
    @SerializedName("login") val id:String,
    @SerializedName("created_at") val date_created:String,
    @SerializedName("updated_at") val date_update:String,
    @SerializedName("followers") val followers:Integer,
    @SerializedName("following") val following:Integer,
    @SerializedName("html_url") val url:String
)