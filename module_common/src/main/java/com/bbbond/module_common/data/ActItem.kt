package com.bbbond.module_common.data

class ActItem {
    var title: String = ""
    var path: String = ""

    fun set(title: String, path: String): ActItem {
        this.title = title
        this.path = path
        return this
    }
}