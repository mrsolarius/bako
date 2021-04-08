package app.bako.view.settings.workcode

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.view.settings.workcode.popup.AddWorkCodePopup
import kotlin.collections.ArrayList

class CodesListActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_codes_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Récupération des data workCode
        buildWorkCodeRecyclerView()
        setAddButton()

    }

    private fun setAddButton() {
        addWorkCode = findViewById(R.id.addWorkCode)
        addWorkCode!!.setOnClickListener{
            val fm: FragmentManager = supportFragmentManager
            val editNameDialogFragment: AddWorkCodePopup = AddWorkCodePopup()
            editNameDialogFragment.show(fm, "fragment_edit_name")
        }
    }
    private fun buildWorkCodeRecyclerView() {
        recyclerViewWorkCode = findViewById<RecyclerView>(R.id.list_WorkCode)
        adapterWorkCode = WorkCodeAdapter(this)
        recyclerViewWorkCode!!.adapter = adapterWorkCode
        recyclerViewWorkCode!!.layoutManager = LinearLayoutManager(this)
        mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)
        mWorkCodeViewModel.readAllData.observe(this, androidx.lifecycle.Observer { workCode ->
            adapterWorkCode!!.setData(workCode)
        })


//        recyclerViewWorkCode!!.setHasFixedSize(true)
//        workCodeLayoutManager = LinearLayoutManager(context)
//        adapterWorkCode = this.workCodeList?.let { WorkCodeAdapter(view.context) }
//        recyclerViewWorkCode!!.layoutManager = workCodeLayoutManager
//        recyclerViewWorkCode!!.adapter = adapterWorkCode
    }
}