package app.bako.view.settings

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import app.bako.R
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.Preference
import app.bako.view.navigation.MainActivity
import app.bako.view.navigation.fragment.CodesListFragment
import java.lang.reflect.Array.newInstance


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
                when (preference?.isEnabled ){
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            return super.onPreferenceTreeClick(preference)
        }

        fun showSettingDialog(){
            val fm: FragmentManager = this.parentFragmentManager
            val editNameDialogFragment: CodesListFragment = CodesListFragment()
            editNameDialogFragment.show(fm, "fragment_edit_name")
        }
    }
}