package org.sopt.soptseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_MESSAGE,"onCreate")
        setContentView(R.layout.activity_main)
        //navigation_graph 활성화
        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    //======Activity 생명주기 LOG
    override fun onStart(){
        super.onStart()
        Log.d(LOG_MESSAGE,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_MESSAGE,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_MESSAGE,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_MESSAGE,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_MESSAGE,"onDestroy")
    }

    companion object {
        const val LOG_MESSAGE = "ActivityLifeCycle : "
    }
}