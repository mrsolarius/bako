package app.bako.view.settings.workcode.popup

import android.annotation.SuppressLint
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
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


class AddWorkCodePopup() : DialogFragment() {
    private var workCode: WorkCode? = null

    private lateinit var editTextCode: TextView
    private lateinit var editHeureDebut: TextView
    private lateinit var editHeureFin: TextView
    private lateinit var colorPicker: Button
    private lateinit var validateEdition: Button
    private lateinit var cancelEdition: FloatingActionButton
    private var color : Int = 0

    private val currentBackgroundColor = -0x1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view: View = inflater.inflate(R.layout.popup_manage_workcode, container, false)

        setViews(view)

        val bundle: Bundle? = arguments
        if (bundle != null) {
            if (bundle.containsKey("workCode")) {
                workCode = bundle.getParcelable<WorkCode>("workCode")
                setElementValuesWithWorkCode()
            }
        }


        setButtonAddOrUpdate()

        setTimePickerOnClick()

        setColorPickerOnClick()

        return view

    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    private fun setTimePickerOnClick() {
        editHeureDebut.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action==MotionEvent.ACTION_DOWN) {
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
                val minute = mcurrentTime[Calendar.MINUTE]
                val mTimePicker = TimePickerDialog(
                    context,
                    { timePicker, selectedHour, selectedMinute -> editHeureDebut.setText("$selectedHour:$selectedMinute") },
                    hour,
                    minute,
                    true
                ) //Yes 24 hour time
                mTimePicker.setTitle("Select Time")
                mTimePicker.show()
            }
            true
        }

        editHeureFin.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action==MotionEvent.ACTION_DOWN) {
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
                val minute = mcurrentTime[Calendar.MINUTE]
                val mTimePicker = TimePickerDialog(
                    context,
                    { timePicker, selectedHour, selectedMinute -> editHeureFin.setText("$selectedHour:$selectedMinute") },
                    hour,
                    minute,
                    true
                ) //Yes 24 hour time
                mTimePicker.setTitle("Select Time")
                mTimePicker.show()
            }
            true
        }
    }

    private fun setElementValuesWithWorkCode() {
        editTextCode.setText(workCode!!.code)
        setDate(workCode!!.startHour, editHeureDebut)
        setDate(workCode!!.endHour, editHeureFin)
        colorPicker.setBackgroundColor(workCode!!.color)
    }

    @SuppressLint("SimpleDateFormat")
    fun setDate(date: Date, view: TextView) { //getting date
        val formatter = SimpleDateFormat("HH:mm") //formating according to my need
        val dateStr: String = formatter.format(date)
        view.text = dateStr
    }

    private fun setButtonAddOrUpdate() {
        validateEdition.setOnClickListener{

            if(isCorrect()) {
                val newWorkCode = editTextCode.text.toString()
                val newHeureDebut = strToHour(editHeureDebut.text.toString())
                val newHeureFin = strToHour(editHeureFin.text.toString())
                val viewColor = colorPicker.background

                if (workCode == null) {
                    workCode = newHeureDebut?.let { it1 ->
                        newHeureFin?.let { it2 ->
                            WorkCode(
                                newWorkCode as String, color,
                                it1, it2
                            )
                        }
                    }

                    //sauvegarde de l'objet
                    val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)
                    mWorkCodeViewModel.addWorkCode(workCode!!)

                } else {
                    workCode!!.code = newWorkCode.toString()
                    workCode!!.color = color
                    if (newHeureDebut != null) {
                        workCode!!.startHour = newHeureDebut
                    }
                    if (newHeureFin != null) {
                        workCode!!.endHour = newHeureFin
                    }
                }

                dismiss()
            }
        }
    }

    private fun isCorrect(): Boolean {
//        val viewColor = colorPicker.background as ColorDrawable
//        val colorId = viewColor.color
        return !(editTextCode.text.isEmpty() && editHeureFin.text.isEmpty() && editHeureDebut.text.isEmpty())
    }

    fun strToHour(strToConvert: String): Date? {
        val formatter = SimpleDateFormat("HH:mm", Locale.FRANCE)
        return formatter.parse(strToConvert)
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
                .setPositiveButton("ok") { dialog, selectedColor, allColors -> changeBackgroundColor(
                    selectedColor
                )
                }
                .setNegativeButton("cancel") { dialog, which ->

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
        cancelEdition = view.findViewById<FloatingActionButton>(R.id.CloseWorkCodeManagement)
    }
}
