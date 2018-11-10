package app.superesenou.ru.neobisnoteapp.ui.main

import android.content.Context
import app.superesenou.ru.neobisnoteapp.model.Note

interface MainContract {
    interface View{
        fun onSuccssecLoadQuery(list: ArrayList<Note>)
    }
    interface Presenter{

        fun getLoadQueary(title: String,context: Context)
    }
}