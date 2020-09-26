package com.cemetery.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Grave(
    @BsonId
    val graveId: String = ObjectId().toString(), //primary key

    val cemeteryId: String,

    val firstName: String,

    val lastName: String,

    val birthDate: String,

    val deathDate: String,

    val marriageYear: String,

    val comment: String,

    val graveNumber: String
) {
}