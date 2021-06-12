package com.akane.appspanelapplication.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.akane.appspanelapplication.R
import com.akane.appspanelapplication.model.Actu
import kotlinx.android.synthetic.main.item_actu.view.*
import java.time.Instant
import java.time.format.DateTimeFormatter

class ActuAdapter(val actus: List<Actu>): RecyclerView.Adapter<ActuAdapter.ViewHolder>() {



    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_actu, parent, false)
    ) {

        private var title: TextView? = null
        private var description: TextView? = null
        private var date: TextView? = null
        private var image: ImageView? = null

        init {
            title = itemView.findViewById(R.id.tvTitleActu)
            description = itemView.findViewById(R.id.tv_DescriptionActu)
            date = itemView.findViewById(R.id.tvDateActu)
            image = itemView.findViewById(R.id.ivActu)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actu = actus[position]

        holder.itemView.tvTitleActu.text = actu.title
        holder.itemView.tv_DescriptionActu.text = actu.description

        // modifier selon la date du jour
        val dateNow = DateTimeFormatter.ISO_INSTANT.format(Instant.now())


        holder.itemView.tvDateActu.text = actu.published_at.toString()

        // image utiliser GlideApp?
        if(actu.picture_url.isEmpty()){
            holder.itemView.ivActu.setImageResource(R.drawable.placeholder)
        }
        // else
//        GlideApp.with(holder.itemView.context)
//            .load(storageReference)
//            .into(holder.itemView.ivActu)


    }

    override fun getItemCount(): Int {
        return actus.size
    }
}