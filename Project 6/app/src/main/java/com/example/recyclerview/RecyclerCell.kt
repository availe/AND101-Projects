package com.example.recyclerview

class RecyclerCell {
    constructor(urlPath: String, headerText: String, leftText: String, rightText: String) {
        this.urlPath = urlPath
        this.headerText = headerText
        this.leftText = leftText
        this.rightText = rightText
    }

    lateinit var urlPath: String
    lateinit var headerText: String
    lateinit var leftText: String
    lateinit var rightText: String
}