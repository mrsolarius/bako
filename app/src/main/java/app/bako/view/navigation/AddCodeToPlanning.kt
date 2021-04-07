package app.bako.view.navigation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayViewModel
import kotlinx.android.synthetic.main.activity_add_code_to_planning.*
import java.text.SimpleDateFormat
import java.util.*


class AddCodeToPlanning : AppCompatActivity() {

    var myCalendar: Calendar = Calendar.getInstance();
    lateinit var choixJournee:EditText
    lateinit var spinnerCodeAffectation:Spinner;
    lateinit var confirmAddCodeToPlanning:Button;

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_code_to_planning)
        setValueOfSpinner()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getViewElements();

        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateText()
        }

        editTextJournee.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                DatePickerDialog(this, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                        myCalendar[Calendar.DAY_OF_MONTH]).show()
            }
            true
        }

        onValidate();

    }

    private fun getViewElements() {
        choixJournee = findViewById<EditText>(R.id.editTextJournee);
        spinnerCodeAffectation = findViewById<View>(R.id.spinnerSelectCodeForAdd) as Spinner
        confirmAddCodeToPlanning = findViewById(R.id.confirmAddCodeToPlanning)
    }

    private fun updateText() {
        val myFormat = "dd/MM/yyyy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        editTextJournee.setText(sdf.format(myCalendar.time))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Go back arrow btn
        finish();
        return true;
    }

    @SuppressLint("SimpleDateFormat")
    fun onValidate(){
        confirmAddCodeToPlanning.setOnClickListener {
            val journeeStr = choixJournee.text.toString()
            val date : Date = SimpleDateFormat("dd/MM/yyyy").parse(journeeStr)!!

            val workCodeSelected = spinnerCodeAffectation.selectedItem.toString()

            val workingDay = WorkingDay(date)
            workingDay.prevWorkCode = workCodeSelected
            workingDay.realWorking = "-1"

            //sauvegarde de l'objet
            val mWorkCodeViewModel = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
            mWorkCodeViewModel.addWorkCode(workingDay)
            finish()
        }
    }

    fun setValueOfSpinner(){
        val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)

        mWorkCodeViewModel.getCodeList().observe(this, { data ->
            data.let {
                val spinnerAdapter =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCodeAffectation.adapter = spinnerAdapter
                spinnerAdapter.addAll();
                spinnerAdapter.notifyDataSetChanged();
            }
        })
    }

}