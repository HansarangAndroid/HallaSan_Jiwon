
# Third Assignment
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/118398467-c6ff4d00-b693-11eb-9879-1e21c2f7cc02.gif" width = 25%>      
<img src ="https://user-images.githubusercontent.com/49470328/118398473-cd8dc480-b693-11eb-9a9b-1fcd651f4c73.gif" width = 25%>    
</p>

# Postman
1. signup but already exist
<img src ="https://user-images.githubusercontent.com/49470328/118398589-4725b280-b694-11eb-988e-52fb7294426b.png" >     

2. signup success!
<img src ="https://user-images.githubusercontent.com/49470328/118398594-4bea6680-b694-11eb-9171-f32dea67d864.png" > 

3. login success!
<img src ="https://user-images.githubusercontent.com/49470328/118398595-4d1b9380-b694-11eb-8dfa-399b49d73728.png" >   

# 

# gitHubApi
![image](https://user-images.githubusercontent.com/49470328/115997367-d0117700-a61d-11eb-9456-f56c80e4191c.png)

* githubRepo
```
data class GithubRepo (
    @SerializedName("name") val name:String,
    @SerializedName("language") val language:String,
    @SerializedName("created_at") val date_created:String,
    @SerializedName("html_url") val url:String
)
```
* getRepoGit
```
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
```

# 느낀점
서버 통신에 대해 좀 더 알 수 있는 기회였습니다. 새롭게 알게 된 callback 방식의 서버 통신방식을 조금 더 공부해서 RXjava 등과 비교 후 저에게 맞는 서버 통신 기법을 찾아야 할 것 같습니다.   
또 이전에 viewModel에서 데이터 조작을 하지 않고 fragment에서 사용했던 부분들을 피드백 받아 이번 세미나에서는 뷰모델에서 서버 통신을 진행해보았습니다. 사용하긴 했지만 아직은 완전하지 않은 MVVM 개념에 조금 더 가까이 다가갈 수 있었던 시간이었습니다. 
