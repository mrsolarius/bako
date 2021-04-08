package app.bako.view.navigation.fragment.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.bako.R
import app.bako.view.navigation.fragment.calendar.popup.AddCodeToPlanningFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManagerWorkCodeOfCalendarFragment : Fragment(){

    lateinit var openOrCloseManager: FloatingActionButton
    lateinit var addCodeToPlanning: FloatingActionButton
    lateinit var updateCodeOfPlanning: FloatingActionButton
    var isOpen = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_add_code_to_planning, container, false)

        openOrCloseManager = view.findViewById<FloatingActionButton>(R.id.openCloseManagementWorkCodeOfCalendar)
        addCodeToPlanning = view.findViewById(R.id.addWorkCodeOfCalendar)
        updateCodeOfPlanning = view.findViewById(R.id.updateWorkCodeOfCalendar)

        //Evenement de clique sur le premier fab
        openOrCloseManager.setOnClickListener{
            if(isOpen){
                closeMenu()
            }else{
                openMenu()
            }
        }

        //ouverture de la popup d'ajout / d'édition sur le clique du boutton
        addCodeToPlanning.setOnClickListener{
            val fm: FragmentManager = parentFragmentManager
            val editNameDialogFragment = AddCodeToPlanningFragment()
            editNameDialogFragment.show(fm, "fragment_add_code_plann")
        }

        return view
    }

    //Fonction pour ouvrire le menu des fab
    private fun openMenu() {
        openOrCloseManager.setImageResource(R.drawable.ic_baseline_close_24)
        addCodeToPlanning.visibility = View.VISIBLE
        updateCodeOfPlanning.visibility = View.VISIBLE
        isOpen = true
    }

    //fonction pour fermer le menu de fab
    private fun closeMenu() {
        openOrCloseManager.setImageResource(R.drawable.ic_baseline_add_24)
        addCodeToPlanning.visibility = View.INVISIBLE
        updateCodeOfPlanning.visibility = View.INVISIBLE
        isOpen = false
    }
}