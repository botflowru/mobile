package com.example.botflow.models

import java.io.Serializable

class Bot : Serializable {
    var id: Int? = null
        private set
    var name: String? = null
    var email: String? = null
        private set
    constructor(name: String?, email: String?) {
        this.name = name
        this.email = email
    }

    constructor(name: String?, email: String?, id: Int?) {
        this.name = name
        this.email = email
        this.id = id
    }

}