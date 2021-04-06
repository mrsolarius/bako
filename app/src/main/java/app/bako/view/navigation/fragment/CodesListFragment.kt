package app.bako.view.navigation.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.adapter.WorkCodeAdapter.kt.WorkCodeAdapter
import app.bako.model.workcode.WorkCode
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.view.navigation.MainActivity
import app.bako.view.navigation.popup.AddWorkCodePopup
import java.util.*
import kotlin.collections.ArrayList

class CodesListFragment : DialogFragment() {
    //Button of page
    private var addWorkCode:Button? = null
    private var addDayOffCode:Button? = null

    //WorkCodeManager
    private var workCodeList:ArrayList<WorkCodeAdapter>? = null
    private var recyclerViewWorkCode: RecyclerView? = null
    private var adapterWorkCode: WorkCodeAdapter? = null
    private var workCodeLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var mWorkCodeViewModel: WorkCodeViewModel

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
        buildWorkCodeRecyclerView(view)
        setAddButton()


        // Inflate the layout for this fragment
        return view
    }

    private fun setAddButton() {
        addWorkCode!!.setOnClickListener{
            val fm: FragmentManager = this.parentFragmentManager
            val editNameDialogFragment: AddWorkCodePopup = AddWorkCodePopup()
            editNameDialogFragment.show(fm, "fragment_edit_name")
        }
    }
    private fun buildWorkCodeRecyclerView(view: View) {
        recyclerViewWorkCode = view.findViewById<RecyclerView>(R.id.list_WorkCode)

        adapterWorkCode = WorkCodeAdapter(view.context)
        recyclerViewWorkCode!!.adapter = adapterWorkCode
        recyclerViewWorkCode!!.layoutManager = LinearLayoutManager(requireContext())
        mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)
        mWorkCodeViewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { workCode ->
            adapterWorkCode!!.setData(workCode)
        })


//        recyclerViewWorkCode!!.setHasFixedSize(true)
//        workCodeLayoutManager = LinearLayoutManager(context)
//        adapterWorkCode = this.workCodeList?.let { WorkCodeAdapter(view.context) }
//        recyclerViewWorkCode!!.layoutManager = workCodeLayoutManager
//        recyclerViewWorkCode!!.adapter = adapterWorkCode
    }
}