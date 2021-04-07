package app.bako.view.navigation.fragment

import android.app.TimePickerDialog
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

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
     * TimePicker pour choisir les temps de préparation et de trajet
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
            } else {
                editTextTimeTravel.setText(text)
            }

            Toast.makeText(context, text, Toast.LENGTH_LONG).show()

        }), hour, minute, true)

        tpd.show()
    }

}