# Second Assignment
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/115997204-49f53080-a61d-11eb-9356-51b06f6dc83e.gif" width = 25%>      
<img src ="https://user-images.githubusercontent.com/49470328/115997193-3fd33200-a61d-11eb-9316-46293b9fb32c.gif" width = 25%>    
<img src ="https://user-images.githubusercontent.com/49470328/115997199-43ff4f80-a61d-11eb-977c-adaa07b77152.gif" width = 25%>
</p>

# MVVM패턴
View - ViewModel - Model로 이루어진 MVVM패턴을 사용했습니다.
1. View    
: 화면을 그리는 레이아웃으로 UI를 다룹니다.    
: Observe를 통해 변화하는 데이터를 이용하여 실시간으로 UI에 적용할 수 있습니다.
2. ViewModel    
: Model에서 변경되는 데이터에 대한 알림을 전달 받고 이를 View에 LiveData형태로 전달합니다.    
: View가 어떻게 생겼는지 모르며, 그렇기 때문에 의존성을 줄일 수 있습니다.
3. Model   
: 데이터를 다루는 곳으로 데이터를 저장하거나, 서버로부터 받은 데이터를 저장합니다.    
: 데이터 변경 시 ViewModel에게 변경 알림을 전송합니다. 

+ 이번 과제에서 Model(Entity,Room, Repository)를 추가로 작성하였습니다.
SignUp을 통해 등록한 회원 정보를 Database에 저장하여 회원 정보를 관리하고, Login시 저장된 회원 정보를 검사하여 로그인이 되도록 구현하였습니다.
(하지만, SignUpViewmodel에서 사용한 repository와 LoginViewmodel에서 사용한 repository가 다른 문제가 발생하여 원인을 찾아보고 있습니다.)

# RecyclerView
반복되는 view의 재활용을 위해 recyclerView를 사용합니다. 
긴 내용의 경우 ...이 나오도록 속성을 사용하였고, '>repository' 버튼을 누르면 fragment_repository로 이동하도록 작성하였습니다.

+ 아이템을 옆으로 슬라이드하여 삭제하고, 위아래로 슬라이드하여 순서를 변경하는 기능을 추가하였습니다. 

# LayoutManager
LinearLayoutManager와 GridLayoutManager를 이용하여 레이아웃을 바꿀 수 있도록 하였습니다. 

# notifyDataSetChanged
notifyDataSetChanged는 어떤 아이템이 변경되면 list에 존재하는 모든 아이템들을 변경하는 action을 하는 아이입니다. 
하지만 이것은 속도 저하를 일으키게 되어 diffutil 이라는 것이 등장했습니다. diffutil은 변하는 아이템만 비교하는 class를 만들어 변경이 발생한 것만 업데이트 하도록 하는 친구입니다. (마치 깃의 version control같은 느낌)
하지만 여전히 비교 연산에 대한 처리가 필요하여 부담이 존재하므로 등장한 것이 listAdapter입니다. listAdpater는 비동기 클래스로 기존에 사용하던 adapter대신 사용하면 됩니다. 

```
class PlaceRecyclerAdapter : ListAdapter<Place, PlaceViewHolder>(PlaceDiffUtilCallback()) {

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaceViewHolder(
        ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}
```
```
val placeAdapter = PlaceRecyclerAdapter()
placeAdapter.submitList(newItems) // 아이템 업데이트
```
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
기존에 UI단에만 구현해 놓았던 MVVM패턴을 database단까지 확장시켜보았습니다. 처음 접하고 경험해보는 부분이라 힘들었는데 한 번 해보니 어느정도 감이 잡히는 것 같습니다.   
RecyclerView에 대해 좀 더 깊이 알아갈 수 있었던 시간이었습니다. 아이템을 상하좌우로 움직이는 애니메이션을 처음 사용해보아서 신기했고 더 많은 기능들을 찾아봐야겠다고 생각했습니다. 
또한 Github Api를 이용하여 정보를 받아왔는데, 다른 API들도 사용하여 다양한 정보들을 이용 해보고 싶습니다. 
