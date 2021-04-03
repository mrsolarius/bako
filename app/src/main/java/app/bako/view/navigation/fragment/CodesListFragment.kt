package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.adapter.WorkCodeAdapter.kt.WorkCodeAdapter
import app.bako.model.WorkCode
import app.bako.view.navigation.MainActivity
import app.bako.view.navigation.popup.AddWorkCodePopup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class CodesListFragment : Fragment() {
    //Button of page
    private var addWorkCode:Button? = null
    private var addDayOffCode:Button? = null

    //WorkCodeManager
    private var workCodeList:ArrayList<WorkCode>? = null
    private var recyclerViewWorkCode: RecyclerView? = null
    private var adapterWorkCode: WorkCodeAdapter? = null
    private var workCodeLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_codes_list, container, false)
        addWorkCode = view.findViewById<Button>(R.id.addWorkCode)
        addDayOffCode = view.findViewById(R.id.addWorkCode)

        //Récupération des data workCode
        loadDataWorkCode()
        buildWorkCodeRecyclerView(view)
        fillWorkCodeAdapterTest()
        setAddButton()


        // Inflate the layout for this fragment
        return view
    }

    private fun setAddButton() {
        addWorkCode!!.setOnClickListener{
            val activityObject: MainActivity = activity as MainActivity
            activityObject.makeCurrentFragment(AddWorkCodePopup())
        }
    }

    private fun fillWorkCodeAdapterTest() {
        workCodeList!!.add(WorkCode("A1", "red", Date(), Date()))
        workCodeList!!.add(WorkCode("A2", "blue", Date(), Date()))
        workCodeList!!.add(WorkCode("A3", "green", Date(), Date()))
        workCodeList!!.add(WorkCode("A4", "yellow", Date(), Date()))
        adapterWorkCode!!.notifyItemRangeInserted(0, workCodeList!!.size)
    }

    private fun buildWorkCodeRecyclerView(view: View) {
        recyclerViewWorkCode = view.findViewById<RecyclerView>(R.id.list_WorkCode)
        recyclerViewWorkCode!!.setHasFixedSize(true)
        workCodeLayoutManager = LinearLayoutManager(context)
        adapterWorkCode = this.workCodeList?.let { WorkCodeAdapter(view.context, it) }
        recyclerViewWorkCode!!.layoutManager = workCodeLayoutManager
        recyclerViewWorkCode!!.adapter = adapterWorkCode
    }

    private fun loadDataWorkCode() {
        val sharedpreference = context?.getSharedPreferences("shared preferences",
            AppCompatActivity.MODE_PRIVATE
        )
        val gson = Gson()
        val json: String? = sharedpreference?.getString("workCodeSharedPreference", null)
        val type: Type = TypeToken.getParameterized(ArrayList::class.java, WorkCode::class.java).type
        workCodeList = gson.fromJson(json, type)

        if(workCodeList == null){
            workCodeList = ArrayList()
        }
    }
}