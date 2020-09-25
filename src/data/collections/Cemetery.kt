package com.cemetery.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class Cemetery(

    @BsonId
    val cemeteryRowId: String = ObjectId().toString(), //default value generated primary key

    val cemeteryName: String,

    val cemeteryLocation: String,

    val cemeteryState: String,

    val cemeteryCounty: String,

    val township: String,

    val range: String,

    val spot: String,

    val firstYear: String,

    val section: String

) {
}