package com.example.desafiomb

class People {

    var name: String = ""
    var email: String = ""
    var cpf: String = ""
    var phone: String = ""
    var password: String = ""

    constructor(name: String, email: String, cpf: String, phone: String, password: String) {
        this.name = name
        this.email = email
        this.cpf = cpf
        this.phone = phone
        this.password = password
    }

    constructor(phone:String){
        this.phone = phone
    }


    fun peopleClass() {

    }

    fun peopleClass(phone: String, email: String) {

    }


}