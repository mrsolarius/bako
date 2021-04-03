package app.bako.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import app.bako.R
import app.bako.view.navigation.fragment.AlarmFragment
import app.bako.view.navigation.fragment.CalendarFragment
import app.bako.view.navigation.fragment.HomeFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        topNavView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.calendar_month -> Toast.makeText(this, "Month", Toast.LENGTH_SHORT).show()
                R.id.calendar_week -> Toast.makeText(this, "Week", Toast.LENGTH_SHORT).show()
                R.id.calendar_planing -> Toast.makeText(this, "Planing", Toast.LENGTH_SHORT).show()
            }
            true
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}