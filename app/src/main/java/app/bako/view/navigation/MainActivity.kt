package app.bako.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import app.bako.R
import app.bako.view.navigation.fragment.AlarmFragment
import app.bako.view.navigation.fragment.CalendarFragment
import app.bako.view.navigation.fragment.HomeFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val homeFragment = HomeFragment()
        val calendarFragment = CalendarFragment()
        val alarmFragment = AlarmFragment()

        this.makeCurrentFragment(calendarFragment);

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> this.makeCurrentFragment(homeFragment)
                R.id.ic_calendar -> this.makeCurrentFragment(calendarFragment)
                R.id.ic_alarm -> this.makeCurrentFragment(alarmFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}