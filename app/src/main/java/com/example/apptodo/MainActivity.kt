package com.example.apptodo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.apptodo.databinding.ActivityMainBinding
import com.example.apptodo.fragments.AddTaskFragment
import com.example.apptodo.fragments.ListFragment
import com.example.apptodo.fragments.SettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    fun pushfragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main,fragment).commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list->pushfragment(ListFragment())
                R.id.setting->pushfragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId=R.id.list
        binding.fab.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.main, AddTaskFragment()).commit()
        }


    }
}