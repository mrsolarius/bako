package app.bako.view.settings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import app.bako.R
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
            //placement du fragment de paramètre dans le layout
            .replace(R.id.settings, SettingsFragment())
            .commit()
        //Mise en place du button retours
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //fermeture de l'activité sur le clique du button retour
        finish()
        return true
    }

    /**
     * SettingsFragment
     *
     * Fragment de gestion des parameter du shared preference
     */
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            //Definition de la resources des préférences
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            val key = preference?.key

            //affichage de l'activité d'ajout de workCode
            if (key.equals("startWorkCode")){
                showSettingDialog()
            }

            //modification du themes utilisée sur clique du switch
            if (key.equals("themeSwitch")){

                val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

                when (prefs.getBoolean(key, true)){
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            return super.onPreferenceTreeClick(preference)
        }

        //Lancement de l'activité lancement de l'activité de l'édition des code
        private fun showSettingDialog(){
            val intent = Intent(activity, CodesListActivity::class.java)
            startActivity(intent)

        }
    }
}