# First Assignment
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

* 이번 과제에서는 DB를 만들 필요가 없다고 느껴 Model(Entity, Room, Repository)를 만들지 않았고, loginFragment와 SignUpFragment의 각 ViewModel만 생성하였습니다.
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/114177015-dc87a580-9976-11eb-8170-c567f5b6ef20.png" width = 40%>
</p>

# 화면 구성
안드로이드에서 제공하는 navigation 기능을 사용하여 fragment간 전환을 구현했습니다.
Host 화면인 fragment_longin, 이후 전환되는 fragment_signup, fragment_home 으로 구성되어 있습니다.  
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/114173918-d8f21f80-9972-11eb-9a04-5cd5bb14dd94.JPG" width = 40%>
</p>

# Activity 생명주기
본 과제에서 Log를 이용하여 Activity의 생명주기를 찍어봐야 하지만 MainActivity를 제외한 화면을 Fragment로 구현하여 개인적으로 공부했던 생명주기 그림을 첨부합니다.   
<p align="center">
<img src ="https://user-images.githubusercontent.com/49470328/114176714-81ee4980-9976-11eb-88e8-1d69c78a098e.png" width = 20%> <img src ="https://user-images.githubusercontent.com/49470328/114173868-c7a91300-9972-11eb-88c6-dc17907cd651.png" width = 70%>
</p>


# LoginFragment, LoginViewModel, fragment_login.xml
1. 
```
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="viewModel"
            type="org.sopt.soptseminar.login.LoginViewModel" />
    </data>
    ...
    기존 XML내용
    ...
</layout>
```
```
<com.google.android.material.textfield.TextInputEditText
            android:id="@+id/EditText_id"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="아이디"
            android:text="@={viewModel.id}"
            android:textSize="15sp" />
```
위와 같은 바인딩을 이용하여 xml과 viewModel을 바인딩 해주었습니다. (viewModel에서 실시간 데이터 처리 가능)

2. 아이디, 비밀번호 Null값 체크를 ViewModel에서 수행해주었습니다.
```
class LoginViewModel : ViewModel(){
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkInputText():Boolean{
        Log.d("LoginViewModelTest",id.value.toString()+"      "+password.value.toString())
        return id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }
}
```

3. Bundle을 이용하여 fragment간 정보 전달을 구현했습니다. (SignUpFragment > LoginFragment)
Bundle값 수신
```
private fun getSafeArgs(){
        arguments?.let{
            //viewModel.name.value = it.getString("NAME").toString()
            viewModel.id.value = it.getString("ID").toString()
            viewModel.password.value = it.getString("PASSWORD").toString()
        }
    }
```

# SignUpFragment, SignUpViewModel, fragment_signup.xml
1. 기타 내용은 Login과 동일합니다.

2. Bundle을 이용하여 fragment 값을 전송합니다. 
```
 val bundle = Bundle()
  bundle.putString("NAME",binding.EditTextName.text.toString())
  bundle.putString("ID",binding.EditTextId.text.toString())
  bundle.putString("PASSWORD",binding.EditTextPassword.text.toString())

  Navigation.findNavController(binding.root).navigate(R.id.passArgs_signup_to_login,bundle)
```

# 느낀점
Navigation, MVVM패턴, Bundle등 처음 보는 개념이 많아 공부할 것이 많았던 시간이었습니다.   
아직 개념이 완전치 않아 많은 공부와 연습이 필요할 것 같습니다.    
메모리 릭 현상등에 대해서도 더 많은 신경을 쓰며 코드를 작성하고 싶습니다.   
