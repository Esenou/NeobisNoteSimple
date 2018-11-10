package app.superesenou.ru.neobisnoteapp.ui.add

import android.content.ContentValues
import android.content.Context
import android.text.Editable
import app.superesenou.ru.neobisnoteapp.data.DbManager


class AddInfoPresenter(val view:AddInfoContract.View):AddInfoContract.Presenter {

    override fun save(context: Context, title: String, description: String, id: Int, flag: Boolean) {
        var dbManager= DbManager(context)
        var values= ContentValues()
        values.put("Title",title)
        values.put("Description",description)

           if(!flag){
               if(id==0){
                   val ID = dbManager.Insert(values)
               }
               view.onSuccssecFinish()
           }
           else {
               if (id != 0) {
                   var selectionArs = arrayOf(id.toString())
                   val ID = dbManager.Update(values, "ID=?", selectionArs)
               }
           }
        }
}