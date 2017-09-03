package me.imzack.app.tick.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.imzack.app.tick.R
import me.imzack.app.tick.view.fragment.SettingsFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager.beginTransaction().replace(R.id.vSettingsLayout, SettingsFragment()).commit()
    }
}
