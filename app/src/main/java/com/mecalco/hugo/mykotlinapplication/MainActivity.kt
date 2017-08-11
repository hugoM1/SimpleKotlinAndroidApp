package com.mecalco.hugo.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mecalco.hugo.mykotlinapplication.home.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmField
        var TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    fun initViews(){
        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, HomeFragment.newInstance(), " HomeFragment")
                .commit()
    }

}
