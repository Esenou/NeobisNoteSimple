package app.superesenou.ru.neobisnoteapp.ui.add


import android.content.Context
import android.text.Editable

interface AddInfoContract {

    interface View{
        fun onSuccssecFinish()
    }
    interface Presenter{
        fun save(context: Context, title: String, description: String, id: Int, flag: Boolean)
    }

}