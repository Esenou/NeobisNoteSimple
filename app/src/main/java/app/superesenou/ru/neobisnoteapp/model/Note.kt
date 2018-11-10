package app.superesenou.ru.neobisnoteapp.model


class Note {

    var ID:Int?=null
    var Name:String?=null
    var Description:String?=null

    constructor(ID:Int,Name:String,Description:String){
        this.ID=ID
        this.Name=Name
        this.Description=Description
    }

}