package com.lks.esemka.esport.activity.mainscreen

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lks.esemka.esport.R
import com.lks.esemka.esport.activity.mainscreen.pemain.PemainFragment
import com.lks.esemka.esport.activity.mainscreen.tim.TeamFragment

class MainScreenContainer : AppCompatActivity() {

    companion object {
        const val USN_KEY = "USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen_container)

        // initialize fragment
        val mFragmentManager = supportFragmentManager
        val teamFragment = TeamFragment()
        val fragment = mFragmentManager.findFragmentByTag(TeamFragment::class.java.simpleName)

        // ambil username dari sign in activity
        val username = intent.getStringExtra(USN_KEY)
        val bundle = Bundle()
        bundle.putString(USN_KEY, username)
        Log.d("MainScreenUsername", "Username : $username")
        teamFragment.arguments = bundle

        if (fragment == null) {
            if (fragment !is TeamFragment) {
                mFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.main_screen_container,
                        teamFragment,
                        TeamFragment::class.java.simpleName
                    )
                    .commit()
            }
        }


    }
}