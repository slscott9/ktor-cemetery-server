package com.cemetery.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Grave(
    @BsonId
    val graveRowId: String = ObjectId().toString(),

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