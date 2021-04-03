package app.bako.view.navigation.popup

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.bako.R
import app.bako.model.WorkCode
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.popup_manage_workcode.*
import java.text.SimpleDateFormat
import java.util.*


class AddWorkCodePopup:AppCompatActivity(){
    private var workCode: WorkCode? = null

    private lateinit var editTextCode: TextView
    private lateinit var editHeureDebut: TextView
    private lateinit var editHeureFin: TextView
    private lateinit var colorPicker: Button
    private lateinit var validateEdition: Button
    private lateinit var cancelEdition: FloatingActionButton

    private val currentBackgroundColor = -0x1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_manage_workcode)

        setViews()

        val extras:Bundle = intent.getExtras() as Bundle;
        if (extras.containsKey("workCode")) {
            workCode = extras.getParcelable<WorkCode>("workCode")!!
            setElementValuesWithWorkCode()
        }else{
            workCode = null
        }

        setButtonAddOrUpdate()

        val dm:DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout(width, height)

        setColorPickerOnClick()

    }

    private fun setElementValuesWithWorkCode() {
        editTextCode.setText(workCode!!.code)
        setDate(workCode!!.startHour, editHeureDebut)
        setDate(workCode!!.endHour, editHeureFin)
//        colorPicker.setBackgroundColor(workCode.color)
    }

    fun setDate(date: Date, view: TextView) { //getting date
        val formatter = SimpleDateFormat("dd.MM.yyyy") //formating according to my need
        val date: String = formatter.format(date)
        view.text = date
    }

    private fun setButtonAddOrUpdate() {
        addOrUpdateCodeTravail.setOnClickListener{
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private fun setColorPickerOnClick() {
        colorPicker.setOnClickListener{
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choisissez une couleur")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener { selectedColor ->
                    Toast.makeText(
                        this,
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
        colorPicker.setBackgroundColor(selectedColor)
    }

    private fun setViews(){
        editTextCode = findViewById(R.id.editTextCodeAffectationTravail)
        editHeureDebut = findViewById(R.id.editTextHeureDebutTravail)
        editHeureFin = findViewById(R.id.editTextHeureFinTravail)
        colorPicker = findViewById(R.id.colorPickerWorkCode)

        validateEdition = findViewById(R.id.addOrUpdateCodeTravail)
        cancelEdition = findViewById<FloatingActionButton>(R.id.CloseWorkCodeManagement)
    }
}
