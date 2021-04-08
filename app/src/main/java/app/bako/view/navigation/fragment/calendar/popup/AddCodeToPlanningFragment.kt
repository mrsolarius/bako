package app.bako.view.navigation.fragment.calendar.popup

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayViewModel
import app.bako.utils.DateFormat.Companion.getFormattedDate
import app.bako.utils.DateFormat.Companion.stringToDate
import kotlinx.android.synthetic.main.activity_add_code_to_planning.*
import java.text.SimpleDateFormat
import java.util.*


class AddCodeToPlanningFragment : DialogFragment() {

    private var myCalendar: Calendar = Calendar.getInstance()
    private lateinit var choixJournee:EditText
    private lateinit var spinnerCodeAffectation:Spinner
    private lateinit var confirmAddCodeToPlanning:Button
    private lateinit var previsionnelOrReel: SwitchCompat
    private lateinit var cancelButton: Button
    var workingDay : WorkingDay? = null
    private var listCodeAffectation:List<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.activity_add_code_to_planning, container, false)
        setValuesOfSpinner(view)

        getViewElements(view)

        val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTextAndSetCurrentWorkingDay()
        }
        //Event listeneur sur la journée
        choixJournee.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                //Ouverture d'un calendrier
                DatePickerDialog(view.context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]).show()
            }
            true
        }

        onSwitchPrevisionnelOrReel()

        //Button d'annulation
        cancelButton.setOnClickListener {
            dismiss()
        }

        onValidate()
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        dialog!!.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun onSwitchPrevisionnelOrReel() {
        //Mise en place du listener sur le boutton prévisionelle ou reel
        previsionnelOrReel.setOnCheckedChangeListener { _, isChecked ->
            if (workingDay != null) {
                if (isChecked) {
                    setValueOfSpinnerByCode(workingDay!!.realWorking)
                } else {
                    setValueOfSpinnerByCode(workingDay!!.prevWorkCode)
                }
            }
        }
    }

    private fun getViewElements(view : View) {
        choixJournee = view.findViewById(R.id.editTextJournee)
        spinnerCodeAffectation = view.findViewById<View>(R.id.spinnerSelectCodeForAdd) as Spinner
        previsionnelOrReel = view.findViewById(R.id.previsionnelOrReel)
        previsionnelOrReel.visibility = View.INVISIBLE
        confirmAddCodeToPlanning = view.findViewById(R.id.confirmAddCodeToPlanning)
        cancelButton = view.findViewById(R.id.cancel_btn)
    }

    private fun updateTextAndSetCurrentWorkingDay() {
        val dateStringSelected = getFormattedDate(myCalendar.time)
        editTextJournee.setText(dateStringSelected)
        //Récupération de l'objet de la BDD
        val mWorkingDayViewModel = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        stringToDate(dateStringSelected)?.let {
            mWorkingDayViewModel.getWorkCodeForWorkDay(it).observe(this, { data ->
                data.let {
                    //Si il n'est pas vide la donnée existe il faut la mettre à jour
                    if(data.isNotEmpty()){
                        workingDay = data[0].workingDay
                        confirmAddCodeToPlanning.text = getString(R.string.update)
                        previsionnelOrReel.visibility = View.VISIBLE
                        setValueOfSpinnerByCode(workingDay!!.prevWorkCode)
                    //Si non pas de nouvelle données il faut ajouter
                    }else{
                        workingDay = null
                        confirmAddCodeToPlanning.text = getString(R.string.add)
                        previsionnelOrReel.visibility = View.INVISIBLE
                    }
                }
            })
        }
    }

    private fun setValueOfSpinnerByCode(code:String?) {
        spinnerCodeAffectation.setSelection(getPositionCodeOnSpinner(code))
    }

    private fun getPositionCodeOnSpinner(prevWorkCode: String?): Int {
        for(i in 0..listCodeAffectation!!.size){
            if(prevWorkCode == listCodeAffectation!![i]){
                return i
            }
        }
        return -1
    }

    @SuppressLint("SimpleDateFormat")
    fun onValidate(){
        confirmAddCodeToPlanning.setOnClickListener {
            val journeeStr = choixJournee.text.toString()
            val date : Date = SimpleDateFormat("dd/MM/yyyy").parse(journeeStr)!!

            val workCodeSelected = spinnerCodeAffectation.selectedItem.toString()

            var mustCreate = false

            if(workingDay == null) {
                workingDay = WorkingDay(date)
                mustCreate = true
                workingDay!!.prevWorkCode = workCodeSelected
                workingDay!!.realWorking = workCodeSelected
            }else{
                if(previsionnelOrReel.isChecked) {
                    workingDay!!.realWorking = workCodeSelected
                }else{
                    workingDay!!.prevWorkCode = workCodeSelected
                }
            }


            //sauvegarde de l'objet
            val mWorkCodeViewModel = ViewModelProvider(this).get(WorkingDayViewModel::class.java)

            if(mustCreate) {
                mWorkCodeViewModel.addWorkCode(workingDay!!)
            }else{
                mWorkCodeViewModel.updateWorkCode(workingDay!!)
            }

            dismiss()
        }
    }

    private fun setValuesOfSpinner(view: View){
        val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)

        mWorkCodeViewModel.getCodeList().observe(this, { data ->
            data.let {
                val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, data)
                listCodeAffectation = data
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCodeAffectation.adapter = spinnerAdapter
                spinnerAdapter.addAll()
                spinnerAdapter.notifyDataSetChanged()
            }
        })
    }

}