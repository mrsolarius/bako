package app.bako.view.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import app.bako.R
import app.bako.view.navigation.fragment.AlarmFragment
import app.bako.view.navigation.fragment.calendar.plannig.CalendarFragment
import app.bako.view.navigation.fragment.HomeFragment
import app.bako.view.settings.SettingsActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //récupération du drawer
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        //Mise en place du button burger
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Mise en place des évènement sur le panning
        topNavView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.calendar_month -> Toast.makeText(this, "Month", Toast.LENGTH_SHORT).show()
                R.id.calendar_week -> Toast.makeText(this, "Week", Toast.LENGTH_SHORT).show()
                R.id.calendar_planing -> Toast.makeText(this, "Planing", Toast.LENGTH_SHORT).show()
                //Settings activity launch
                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        //Initialisation des 3 fragment du menu principal
        val homeFragment = HomeFragment()
        val calendarFragment = CalendarFragment()
        val alarmFragment = AlarmFragment()
        //Mise en place du calendrier comme fragment part default
        this.makeCurrentFragment(calendarFragment)
        //mise en place du listener de navigation
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> this.makeCurrentFragment(homeFragment)
                R.id.ic_calendar -> this.makeCurrentFragment(calendarFragment)
                R.id.ic_alarm -> this.makeCurrentFragment(alarmFragment)
            }
            true
        }
    }

    //fonction de replacement du fragment actuel par un nouveau
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //prise en charge du clique sur le burger pour afficher le drawer
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}