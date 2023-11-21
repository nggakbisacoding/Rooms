package com.trpl.rooms

object UserObject {
    private var listdata = mutableListOf<User>()

    fun setData(name: String, phone:String, email:String, pass: String) {
        listdata.add(User(name, phone, email,  pass))
    }

    fun getAllData(): List<User> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): User {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,name:String,phone:String, email:String, pass:String)
    {
        listdata[pos].name=name
        listdata[pos].phone=phone
        listdata[pos].email=email
        listdata[pos].pass=pass
    }

}