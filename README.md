# Second Assignment
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/114176250-dba24400-9975-11eb-8ab2-6a427c7a3bb1.gif" width = 25%>      <img src ="https://user-images.githubusercontent.com/49470328/114176272-e230bb80-9975-11eb-9c81-68708095de82.gif" width = 25%>
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

#LayoutManager
LinearLayoutManager와 GridLayoutManager를 이용하여 레이아웃을 바꿀 수 있도록 하였습니다. 

# notifyDataSetChanged 대신 


# 느낀점
Navigation, MVVM패턴, Bundle등 처음 보는 개념이 많아 공부할 것이 많았던 시간이었습니다.   
아직 개념이 완전치 않아 많은 공부와 연습이 필요할 것 같습니다.    
메모리 릭 현상등에 대해서도 더 많은 신경을 쓰며 코드를 작성하고 싶습니다.   
