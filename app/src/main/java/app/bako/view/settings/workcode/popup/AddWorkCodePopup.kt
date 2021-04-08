package app.bako.view.settings.workcode.popup

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCode
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.utils.DateFormat.Companion.hourToString
import app.bako.utils.DateFormat.Companion.stringToHour
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import java.util.*

/**
 * AddWorkCodePopup
 * Popup d'ajout des code de travail
 */
class AddWorkCodePopup : DialogFragment() {
    private var workCode: WorkCode? = null

    private lateinit var editTextCode: TextView
    private lateinit var editHeureDebut: TextView
    private lateinit var editHeureFin: TextView
    private lateinit var colorPicker: Button
    private lateinit var validateEdition: Button
    private lateinit var cancelEdition: Button
    private var color : Int = 0

    private val currentBackgroundColor = -0x1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view: View = inflater.inflate(R.layout.popup_manage_workcode, container, false)

        setViews(view)

        //récupération du bundle de données si click sur le button edition dans le workCodeAdapter
        val bundle: Bundle? = arguments
        if (bundle != null) {
            if (bundle.containsKey("workCode")) {
                workCode = bundle.getParcelable("workCode")
                setElementValuesWithWorkCode()
            }
        }


        setButtonAddOrUpdate()

        setTimePickerOnClick()

        setColorPickerOnClick()

        //fermeture sur le click du button cancel
        cancelEdition.setOnClickListener {
            dismiss()
        }

        return view

    }

    override fun onStart() {
        super.onStart()
        //Redefinition de la taille de la fenêtre à 95% de la largeur
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        dialog!!.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    private fun setClickListener(registeredView : TextView){
        registeredView.setOnTouchListener { _, motionEvent ->
            if(motionEvent.action==MotionEvent.ACTION_DOWN) {
                val currentTime = Calendar.getInstance()
                val hour = currentTime[Calendar.HOUR_OF_DAY]
                val minute = currentTime[Calendar.MINUTE]
                val mTimePicker = TimePickerDialog(
                    context,
                    { _, selectedHour, selectedMinute -> registeredView.text = "$selectedHour:$selectedMinute" },
                    hour,
                    minute,
                    true
                )
                mTimePicker.show()
            }
            true
        }
    }

    private fun setTimePickerOnClick() {
        //setup picker dialog
        setClickListener(editHeureDebut)
        setClickListener(editHeureFin)
    }

    /**
     * Initialisation des code si grace au bundle si edition
     */
    private fun setElementValuesWithWorkCode() {
        validateEdition.text = resources.getString(R.string.update)
        editTextCode.text = workCode!!.code
        editHeureDebut.text = hourToString(workCode!!.startHour)
        editHeureFin.text = hourToString(workCode!!.endHour)
        colorPicker.setBackgroundColor(workCode!!.color)
    }

    /**
     * Appelais lorsque l'on ajout la validation
     */
    private fun setButtonAddOrUpdate() {
        validateEdition.setOnClickListener{
            if(isCorrect()) {
                val newWorkCode = editTextCode.text.toString()
                val newHeureDebut = stringToHour(editHeureDebut.text.toString())
                val newHeureFin = stringToHour(editHeureFin.text.toString())
                //sauvegarde de l'objet
                val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)
                if (workCode == null) {
                    //création du code de travail
                    workCode = newHeureDebut?.let { it1 ->
                        newHeureFin?.let { it2 ->
                            WorkCode(
                                newWorkCode, color,
                                it1, it2
                            )
                        }
                    }

                    //ajout du code de travail à la BDD
                    mWorkCodeViewModel.addWorkCode(workCode!!)

                } else {
                    workCode!!.code = newWorkCode
                    workCode!!.color = color
                    if (newHeureDebut != null) {
                        workCode!!.startHour = newHeureDebut
                    }
                    if (newHeureFin != null) {
                        workCode!!.endHour = newHeureFin
                    }
                    //Mise à jour du code de travail
                    mWorkCodeViewModel.updateWorkCode(workCode!!)
                }

                dismiss()
            }
        }
    }

    private fun isCorrect(): Boolean {
        //Verification de la validité du formulaire
        return !(editTextCode.text.isEmpty() && editHeureFin.text.isEmpty() && editHeureDebut.text.isEmpty())
    }

    private fun setColorPickerOnClick() {
        colorPicker.setOnClickListener{
            ColorPickerDialogBuilder
                .with(context)
                .setTitle("Choisissez une couleur")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener { selectedColor ->
                    Toast.makeText(
                        context,
                        "onColorSelected: 0x" + Integer.toHexString(
                            selectedColor
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
                //changement de couleur de fond en fonction de la couleur
                .setPositiveButton("ok") { _, selectedColor, _ -> changeBackgroundColor(
                    selectedColor
                )
                }
                .setNegativeButton("cancel") { _, _ ->

                }
                .build()
                .show()
        }
    }

    private fun changeBackgroundColor(selectedColor: Int) {
        color = selectedColor
        colorPicker.setBackgroundColor(selectedColor)
    }

    private fun setViews(view: View) {
        editTextCode = view.findViewById(R.id.editTextCodeAffectationTravail)
        editHeureDebut = view.findViewById(R.id.editTextHeureDebutTravail)
        editHeureFin = view.findViewById(R.id.editTextHeureFinTravail)
        colorPicker = view.findViewById(R.id.colorPickerWorkCode)

        validateEdition = view.findViewById(R.id.addOrUpdateCodeTravail)
        cancelEdition = view.findViewById(R.id.cancel_dialog_btn)
    }
}
