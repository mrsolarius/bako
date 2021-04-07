package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import app.bako.R
import kotlinx.android.synthetic.main.fragment_alarm.*


/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {

    private var wakeUpTime = 8*60 + 0 // heure supposée de début de travail en minutes
    private var preparationTime = 0
    private var travelTime = 0
    private var totalTime = 0

    private lateinit var sharedPref: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

        // Creation de ma sharedPreference
        sharedPref = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        // On recupère les valeurs des sharedPreferences si elles existent sinon on set les valeurs à 0
        preparationTime = sharedPref.getInt("preparationTime", 0)
        travelTime = sharedPref.getInt("travelTime", 0)
        totalTime = sharedPref.getInt("totalTime", 0)

        // Activation d'un TimePicker quand on appuie sur l'image bouton du temps de préparation
        val button: ImageButton = view.findViewById<View>(R.id.imageButtonPreparationTime) as ImageButton
        button.setOnClickListener { v ->
            if (v != null) {
                clickTimePicker(v, true)
            }
        }

        // Activation d'un TimePicker quand on appuie sur l'image bouton du temps de trajet
        val button2: ImageButton = view.findViewById<View>(R.id.imageButtonTravelTime) as ImageButton
        button2.setOnClickListener { v ->
            if (v != null) {
                clickTimePicker(v, false)
            }
        }

        return view
    }

    /**
     * TimePicker pour choisir les temps de préparation et de trajet. Cela met automatiquement à jour le temps du prochain réveil
     * @param view: View actuelle
     * @param whichButton: Boolean qui permet de choisir si on set le temps de trajet ou celui de préparation
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickTimePicker(view: View, whichButton: Boolean) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

            val text = "$h : $m"
            if (whichButton) {
                editTextTimePreparation.setText(text)
                saveSharedPreference("preparationTime", h * 60 + m)
            } else {
                editTextTimeTravel.setText(text)
                saveSharedPreference("travelTime", h * 60 + m)
            }

            setNextWakeUpTime()

            Toast.makeText(context, text, Toast.LENGTH_LONG).show()

        }), hour, minute, true)

        tpd.show()
    }

    /**
     * Mise a jour du temps du prochain réveil
     */
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private fun setNextWakeUpTime()
    {
        var retour = wakeUpTime - sharedPref.getInt("preparationTime", 0) - sharedPref.getInt("travelTime", 0)

        saveSharedPreference("totalTime", retour)
        editTextTimeTotal.setText("${retour / 60} : ${retour % 60}")
    }

    /**
     * Sauvegarde des sharedPreferences
     * @param KEY_NAME: String qui represente le nom de la sharedPreference
     * @param value: Int qui est la valeur à sauvegarder dans la sharedPreference
     */
    private fun saveSharedPreference(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)
        editor.apply()
    }
}