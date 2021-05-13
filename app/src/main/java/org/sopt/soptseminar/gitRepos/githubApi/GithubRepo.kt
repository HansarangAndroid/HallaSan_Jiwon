package org.sopt.soptseminar.gitRepos.githubApi

import com.google.gson.annotations.SerializedName

data class GithubRepo (
    @SerializedName("name") val name:String,
    @SerializedName("language") val language:String,
    @SerializedName("created_at") val date_created:String,
    @SerializedName("html_url") val url:String
)