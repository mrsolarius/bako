package app.bako.view.navigation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel


class AddCodeToPlanning : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_code_to_planning)

        setValueOfSpinner()
    }

    fun setValueOfSpinner(){
        val mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)

        val spinner = findViewById<View>(R.id.spinnerSelectCodeForAdd) as Spinner

        var myList: List<String> = emptyList()

        mWorkCodeViewModel.listWorkCode.observe(this, androidx.lifecycle.Observer{
            t ->
            myList = t
        })
        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinnerAdapter.addAll();
        spinnerAdapter.notifyDataSetChanged();
    }
}