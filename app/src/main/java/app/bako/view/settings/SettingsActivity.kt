package app.bako.view.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import app.bako.R
import androidx.fragment.app.Fragment
import androidx.preference.Preference
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import app.bako.view.settings.workcode.CodesListActivity


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Go back arrow btn
        finish();
        return true;
    }

    fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            val key = preference?.key
            if (key.equals("startWorkCode")){
                showSettingDialog()
            }

            if (key.equals("themeSwitch")){

                val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
                var editor = prefs.getBoolean(key, true)

                when (editor){
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                Log.i("TAG", "onPreferenceTreeClick: "+editor)
            }

            return super.onPreferenceTreeClick(preference)
        }

        fun showSettingDialog(){
            val intent = Intent(activity, CodesListActivity::class.java)
            startActivity(intent)

        }
    }
}