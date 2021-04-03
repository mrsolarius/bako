package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.content.Intent
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CodesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
            activityObject.makeCurrentFragment(CodesListFragment())
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
        val gson: Gson = Gson()
        val json: String? = sharedpreference?.getString("workCodeSharedPreference", null)
        val type: Type = TypeToken.getParameterized(ArrayList::class.java, WorkCode::class.java).type
        workCodeList = gson.fromJson(json, type)

        if(workCodeList == null){
            workCodeList = ArrayList()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CodesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CodesListFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}