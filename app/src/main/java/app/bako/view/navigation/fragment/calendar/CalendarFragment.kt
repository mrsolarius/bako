package app.bako.view.navigation.fragment.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import app.bako.R
import app.bako.model.workcode.WorkCodeViewModel
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayDao
import app.bako.model.workingday.WorkingDayRepository
import app.bako.model.workingday.WorkingDayViewModel
import app.bako.view.navigation.fragment.ManagerWorkCodeOfCalendarFragment
import kotlinx.android.synthetic.main.fragment_calendar.view.*

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
         * SETUP RECYCLER VIEW AND LOAD DATA
         */
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val adapter = CalendarRecyclerAdapter()
        val recyclerView = view.calendarRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mWorkingDayRepository = ViewModelProvider(this).get(WorkingDayViewModel::class.java)
        mWorkingDayRepository.getAllWorkingDayWithWorkCode().observe(viewLifecycleOwner, { workingDay ->
            workingDay.let {
                adapter.setData(workingDay)
            }
        })
        recyclerView.adapter = adapter

        /*
         * SETUP FRAGMENT FAB BUTTON
         */
        val ft: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        ft.replace(R.id.managerWorkCodeOfCalendar, ManagerWorkCodeOfCalendarFragment())
        ft.commit()
        return view
    }
}