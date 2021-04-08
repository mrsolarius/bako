package app.bako.view.settings.workcode

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.view.settings.workcode.popup.AddWorkCodePopup

class CodesListActivity : AppCompatActivity() {
    //Button of page
    private var addWorkCode:ImageButton? = null

    private var recyclerViewWorkCode: RecyclerView? = null
    private var adapterWorkCode: WorkCodeAdapter? = null
    private lateinit var mWorkCodeViewModel: WorkCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codes_list)
        //Flèche de retour
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Récupération des data workCode
        buildWorkCodeRecyclerView()
        setAddButton()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Cloture de l'activité
        finish()
        return true
    }

    private fun setAddButton() {
        addWorkCode = findViewById(R.id.addWorkCode)
        addWorkCode!!.setOnClickListener{
            //Affichage du fragment AddWorkCodePopup dans un alertView
            val fm: FragmentManager = supportFragmentManager
            val editNameDialogFragment = AddWorkCodePopup()
            editNameDialogFragment.show(fm, "fragment_edit_name")
        }
    }
    private fun buildWorkCodeRecyclerView() {
        recyclerViewWorkCode = findViewById(R.id.list_WorkCode)
        adapterWorkCode = WorkCodeAdapter(this)
        recyclerViewWorkCode!!.adapter = adapterWorkCode
        recyclerViewWorkCode!!.layoutManager = LinearLayoutManager(this)
        //Récupération du model provider de la BDD
        mWorkCodeViewModel = ViewModelProvider(this).get(WorkCodeViewModel::class.java)
        //récupération des données du modèle
        mWorkCodeViewModel.readAllData.observe(this, { workCode ->
            adapterWorkCode!!.setData(workCode)
        })
    }
}