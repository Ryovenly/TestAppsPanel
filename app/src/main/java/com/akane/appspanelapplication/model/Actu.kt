package com.akane.appspanelapplication.model

import org.json.JSONObject
import java.sql.Timestamp
import java.util.*


class Actu(
    var id: String,
    var title:String,
    var description:String,
    var latitude:Float,
    var longitude:Float,
    var picture_url:String,
    var published_at:Timestamp,

    ) {
}

//class Actu(json: String):JSONObject(json){
//    var id = this.optInt("id")
//    var title = this.optString("title")
//    var description= this.optString("description")
//    var latitude = this.optLong("latitude")
//    var longitude = this.optLong("longitude")
//    var picture_url= this.optString("picture_url")
//    var published_at = this.optInt("published_at")
//
//}