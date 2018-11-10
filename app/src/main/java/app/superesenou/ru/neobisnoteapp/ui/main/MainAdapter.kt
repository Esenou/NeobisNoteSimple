package app.superesenou.ru.neobisnoteapp.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import app.superesenou.ru.neobisnoteapp.R
import app.superesenou.ru.neobisnoteapp.data.DbManager
import app.superesenou.ru.neobisnoteapp.model.Note
import kotlinx.android.synthetic.main.item_section_main.view.*


class MainAdapter(private var listNotes: ArrayList<Note>, val listener: Listener, var mainActivity: MainActivity):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     lateinit var presenter:MainPresenter
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view= LayoutInflater.from(p0.context).inflate(R.layout.item_section_main,p0,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
      return listNotes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)?.bind( listNotes.get(position))
    }
inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    fun bind(position: Note){
        var myNote=listNotes[0]
        itemView.tvTitle.text=position.Name
        itemView.tvDes.text=position.Description
        itemView.tag=position
        itemView.ivDelete.setOnClickListener( View.OnClickListener {
            listener.setOnItemClickDelete(position)
        })

        itemView.setOnClickListener( View.OnClickListener{v->
            val position =v.tag as Note
            listener.setOnItemClick(position)
        })
    }
}
    interface Listener{
        fun setOnItemClick(position: Note)
        fun setOnItemClickDelete(position: Note)
    }

}