package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayViewModel
import kotlinx.android.synthetic.main.activity_add_code_to_planning.*
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("UseSwitchCompatOrMaterialCode")
class AddCodeToPlanningFragment : DialogFragment() {

    var myCalendar: Calendar = Calendar.getInstance();
    lateinit var choixJournee:EditText
    lateinit var spinnerCodeAffectation:Spinner
    lateinit var confirmAddCodeToPlanning:Button
    lateinit var previsionnelOrReel: Switch
    lateinit var cancelButton: Button
    var workingDay : WorkingDay? = null
    var listCodeAffectation:List<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.activity_add_code_to_planning, container, false)
        setValuesOfSpinner(view)

        getViewElements(view);

        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTextAndSetCurrentWorkingDay()
        }

        choixJournee.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                DatePickerDialog(view.context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]).show()
            }
            true
        }

        onSwitchPrevisionnelOrReel()

        cancelButton.setOnClickListener {
            dismiss()
        }

        onValidate();
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
        dialog!!.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//
//        confirmAddCodeToPlanning.setOnClickListener{
//            var test = workingDay
//            print("")
//        }
    }

    private fun onSwitchPrevisionnelOrReel() {
//        previsionnelOrReel.setOnClickListener {
//            if (previsionnelOrReel.) {
//                Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Unselected", Toast.LENGTH_SHORT).show()
//            }
//        }
        previsionnelOrReel.setOnCheckedChangeListener { compoundButton, isChecked ->
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
        choixJournee = view.findViewById<EditText>(R.id.editTextJournee);
        spinnerCodeAffectation = view.findViewById<View>(R.id.spinnerSelectCodeForAdd) as Spinner
        previsionnelOrReel = view.findViewById(R.id.previsionnelOrReel)
        previsionnelOrReel.visibility = View.INVISIBLE
        confirmAddCodeToPlanning = view.findViewById(R.id.confirmAddCodeToPlanning)
        cancelButton = view.findViewById(R.id.cancel_btn)
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextAndSetCurrentWorkingDay() {
        val myFormat = "dd/MM/yyyy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        val value = myCalendar.time

        val dateStringSelected = sdf.format(value)

        val dateSelected = sdf.parse(dateStringSelected)

        editTextJournee.setText(dateStringSelected)

        val mWorkingDayViewModel = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        mWorkingDayViewModel.getWorkCodeForWorkDay(dateSelected!!).observe(this, { data ->
            data.let {
                if(data.isNotEmpty()){
                    workingDay = data[0].workingDay
                    confirmAddCodeToPlanning.text = "Mettre à jour"
                    previsionnelOrReel.visibility = View.VISIBLE
                    setValueOfSpinnerByCode(workingDay!!.prevWorkCode)
                }else{
                    workingDay = null
                    confirmAddCodeToPlanning.text = "Ajouter"
                    previsionnelOrReel.visibility = View.INVISIBLE
                }
            }
        })
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

    fun setValuesOfSpinner(view: View){
        val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)

        mWorkCodeViewModel.getCodeList().observe(this, { data ->
            data.let {
                val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, data)
                listCodeAffectation = data
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCodeAffectation.adapter = spinnerAdapter
                spinnerAdapter.addAll();
                spinnerAdapter.notifyDataSetChanged();
            }
        })
    }

}