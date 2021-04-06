package app.bako.view.navigation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.bako.R
import app.bako.view.navigation.AddCodeToPlanning
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManagerWorkCodeOfCalendarFragment : Fragment(){

    lateinit var openOrCloseManager: FloatingActionButton
    lateinit var addCodeToPlanning: FloatingActionButton
    lateinit var updateCodeOfPlanning: FloatingActionButton
    var isOpen = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add_code_to_planning, container, false)

        openOrCloseManager = view.findViewById<FloatingActionButton>(R.id.openCloseManagementWorkCodeOfCalendar)
        addCodeToPlanning = view.findViewById(R.id.addWorkCodeOfCalendar)
        updateCodeOfPlanning = view.findViewById(R.id.updateWorkCodeOfCalendar)

        openOrCloseManager.setOnClickListener{
            if(isOpen){
                closeMenu()
            }else{
                openMenu()
            }
        }

        addCodeToPlanning.setOnClickListener{
            val intent: Intent = Intent(context, AddCodeToPlanning::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun openMenu() {
        openOrCloseManager.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
        addCodeToPlanning.visibility = View.VISIBLE
        updateCodeOfPlanning.visibility = View.VISIBLE
        isOpen = true
    }

    private fun closeMenu() {
        openOrCloseManager.setImageResource(android.R.drawable.ic_menu_add)
        addCodeToPlanning.visibility = View.INVISIBLE
        updateCodeOfPlanning.visibility = View.INVISIBLE
        isOpen = false
    }
}