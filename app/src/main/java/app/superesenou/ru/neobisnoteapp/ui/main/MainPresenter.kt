package app.superesenou.ru.neobisnoteapp.ui.main

import android.content.Context
import app.superesenou.ru.neobisnoteapp.data.DbManager
import app.superesenou.ru.neobisnoteapp.model.Note

class MainPresenter(val view: MainContract.View):MainContract.Presenter {

    var listNotes=ArrayList<Note>()

    override fun getLoadQueary(title: String,context:Context) {

        var dbManager=DbManager(context)
        val projections= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        listNotes.clear()
        if(cursor.moveToFirst()){
            do{
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))
                listNotes.add(Note(ID,Title,Description))

            }while (cursor.moveToNext())
        }

        view.onSuccssecLoadQuery(listNotes)
    }


}