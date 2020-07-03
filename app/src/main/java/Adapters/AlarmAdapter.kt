package Adapters

import Model.AlarmModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waqar.mathalarm.R
import kotlinx.android.synthetic.main.alarm_row.view.*


class AlarmAdapter(var context:Context,var list: ArrayList<AlarmModel>,var listener: (AlarmModel) -> Unit) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(LayoutInflater.from(context).inflate(R.layout.alarm_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(list[position],listener)
    }

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AlarmModel, listener: (AlarmModel) -> Unit) = with(itemView)
        {

            s_alarm.isChecked = item.isAlarmOn
            setOnClickListener { listener(item) }
        }
    }
}