package com.cemetery.data

import com.cemetery.data.collections.Cemetery
import com.cemetery.data.collections.Grave
import com.cemetery.data.collections.User
import com.mongodb.client.model.InsertManyOptions
import io.ktor.html.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.replaceUpsert
import org.litote.kmongo.updateMany
import org.litote.kmongo.upsert


val client = KMongo.createClient().coroutine
private val database = client.getDatabase("cemetery_database")

private val cemeteryCollection = database.getCollection<Cemetery>()
private val graveCollection = database.getCollection<Grave>()
private val usersCollection = database.getCollection<User>()

private val userCollection = database.getCollection<User>()

suspend fun registerUser(user: User) : Boolean {
    return userCollection.insertOne(user).wasAcknowledged()
}

suspend fun checkIfUserExists(emailQuery: String) : Boolean {
    return userCollection.findOne(User::email eq emailQuery) != null
}

suspend fun checkPasswordForEmail(emailQuery: String, passwordToCheck: String) : Boolean {
    val actualPassword = userCollection.findOne(User::email eq emailQuery)?.password ?: return false

    return actualPassword == passwordToCheck
}

suspend fun getCemeteriesForUser() : List<Cemetery> {
    return cemeteryCollection.find().toList()
}

suspend fun getAllGravesForUser() : List<Grave> {
    return graveCollection.find().toList()
}

suspend fun insertNewGraves(newGraveList : List<Grave>) : Boolean {

    return graveCollection.insertMany( newGraveList).wasAcknowledged()
}

suspend fun insertNewCemeteries(newCemList : List<Cemetery>) : Boolean {
    return cemeteryCollection.insertMany(newCemList).wasAcknowledged()
}

/*
    TODO implement a post for update grave list test is with post man to see if we update current graves with a new grave list passed from a post
 */

suspend fun updateGraves(updateGraveList : List<Grave>) : Boolean {

    updateGraveList.forEachIndexed { index, grave ->

        val goodUpdate = graveCollection.updateOne(Grave::graveId eq grave.graveId, grave, upsert()).wasAcknowledged()
        if(!goodUpdate){
            return false
        }
    }
    return true
}

suspend fun updateCemeteries(updateCemeteryList: List<Cemetery>): Boolean {
    updateCemeteryList.forEachIndexed { index, cemetery ->

    }
}