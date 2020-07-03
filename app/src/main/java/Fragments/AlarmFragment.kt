package Fragments


import Adapters.AlarmAdapter
import Model.AlarmModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waqar.mathalarm.MainActivity

import com.waqar.mathalarm.R
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_set_alarm.*

/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = arrayListOf<AlarmModel>()
        for(i in 0..10){
            list.add(AlarmModel(i,i,true))
        }
        alarmRecyclerView.layoutManager = LinearLayoutManager(activity!!)
        alarmRecyclerView.adapter = AlarmAdapter(activity!!,list){
            var dialog = AlertDialog.Builder(activity!!)
                .setView(R.layout.fragment_set_alarm)
                .setCancelable(false)
                .show()

            rv_days.layoutManager = LinearLayoutManager(activity!!,LinearLayoutManager.HORIZONTAL, true)
        }
    }
}
