package ru.slybeaver.currentweatherdemo.ui.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import ru.slybeaver.currentweatherdemo.R
import ru.slybeaver.currentweatherdemo.utils.AppSettings

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
class CityListAdapter(var items: ArrayList<String>, val listener: (String?) -> Unit)
    : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_city_line, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.txtCity.context
        holder.txtCity.background = ContextCompat.getDrawable(context, R.drawable.default_bottom_delimiter)
        holder.txtCity.setTextColor(ContextCompat.getColor(context, R.color.default_text_color))
        if (position == 0) {
            holder.txtCity.text = context.getString(R.string.current_location)
            holder.txtCity.setOnClickListener {
                listener(null)
            }
            if (AppSettings.instance.currentCity == null) {
                holder.txtCity.background = ContextCompat.getDrawable(context, R.drawable.selected_bottom_delimiter)
                holder.txtCity.setTextColor(ContextCompat.getColor(context, R.color.snow_white))

            }
        } else {
            holder.txtCity.text = items[position-1]
            holder.txtCity.setOnClickListener {
                listener(items[position-1])
            }
            if (items[position-1] == AppSettings.instance.currentCity) {
                holder.txtCity.background = ContextCompat.getDrawable(context, R.drawable.selected_bottom_delimiter)
                holder.txtCity.setTextColor(ContextCompat.getColor(context, R.color.snow_white))

            }
        }
        animate(holder.txtCity)
    }

    private fun animate(root: View) {
        AnimationUtils
                .loadAnimation(root.context, android.R.anim.slide_in_left)
                .let(root::startAnimation)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCity: TextView = itemView.findViewById<TextView>(R.id.txtCity)
    }
}