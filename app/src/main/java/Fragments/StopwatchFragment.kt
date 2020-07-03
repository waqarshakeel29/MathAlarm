package Fragments


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.waqar.mathalarm.R
import com.waqar.mathalarm.startStopwatchService
import com.waqar.mathalarm.stopStopwatchService
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class StopwatchFragment : Fragment() {


    open val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("TAG1","RECIVING")
            tv_stopwatch.setText(intent.getStringExtra("TIME"))
//            Log.d("TIMER",intent.getStringExtra("TIME"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStart.setOnClickListener {
            activity!!.startStopwatchService()
        }

        btnStop.setOnClickListener {
            activity!!.stopStopwatchService()
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(activity!!).unregisterReceiver(mBroadcastReceiver)
    }


    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(activity!!)
            .registerReceiver(mBroadcastReceiver, IntentFilter("Stopwatch"))
    }


}
