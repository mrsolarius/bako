package app.bako.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import app.bako.R
import app.bako.view.navigation.fragment.AlarmFragment
import app.bako.view.navigation.fragment.CalendarFragment
import app.bako.view.navigation.fragment.CodesListFragment
import app.bako.view.navigation.fragment.HomeFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar:Toolbar

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        val toolbar = findViewById<Toolbar>(R.id.)

//        toolbar = findViewById(R.id.toolBarMainActivity)
//
//        setSupportActionBar(toolbar)


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            toggle.onOptionsItemSelected(item) -> {
                return true
            }
            item.itemId == R.id.setCodeAffectations -> {
                this.makeCurrentFragment(CodesListFragment())
            }
            item.itemId == R.id.setOtherSettings -> {
                Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show()
            }
            item.itemId == R.id.setConfig -> {
                this.makeCurrentFragment(CodesListFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}