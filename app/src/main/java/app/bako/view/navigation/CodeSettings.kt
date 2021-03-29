package app.bako.view.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.adapter.WorkCodeAdapter.kt.WorkCodeAdapter
import app.bako.model.WorkCode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type

class CodeSettings : AppCompatActivity() {
    private var textCodeAffectationTravail: TextView? = null
    private var textHeureFinTravail: TextView? = null
    private var textHeureDebutTravail: TextView? = null
    private var btnAdd: Button? = null
    private var workCodeList:ArrayList<WorkCode>? = null
    private var recyclerViewTravail: RecyclerView? = null
    private var adapterWorkCode: WorkCodeAdapter? = null
    private var workCodeLayoutManager:RecyclerView.LayoutManager? = null
//    private var TaskTravailAdapter:AdapterView<> = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_settings)

        textCodeAffectationTravail = findViewById(R.id.editTextCodeAffectationTravail)
        textHeureDebutTravail = findViewById(R.id.editTextHeureDebutTravail)
        textHeureFinTravail = findViewById(R.id.editTextHeureFinTravail)
        btnAdd = findViewById<Button>(R.id.addOrUpdateCodeTravail)

        loadData()
//        buildRecyclerView()
//        setInsertButton()


        btnAdd?.setOnClickListener(View.OnClickListener {
            saveData()
        })


//        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        taskEntry =
    }

    private fun saveData(){
        var sharedpreference = getSharedPreferences("shared preferences", MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedpreference.edit()
        var gson:Gson = Gson()
        var json:String = gson.toJson(workCodeList)
        editor.putString("task list", json)
        editor.apply()
    }

    private fun loadData(){
        var sharedpreference = getSharedPreferences("shared preferences", MODE_PRIVATE)
        var gson:Gson = Gson()
        var json: String? = sharedpreference.getString("task list", null)
//        var type : Type = TypeToken<ArrayList<WorkCode>>() {}.type
//        workCodeList = gson.fromJson(json, type)
//
//        if(workCodeList == null){
//            workCodeList = ArrayList()
//        }
    }
}