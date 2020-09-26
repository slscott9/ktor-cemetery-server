package com.cemetery.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class Cemetery(

    @BsonId
    val cemeteryId: String = ObjectId().toString(), //default value generated primary key

    val name: String,

    val location: String,

    val state: String,

    val county: String,

    val township: String,

    val range: String,

    val spot: String,

    val firstYear: String,

    val section: String

) {
}