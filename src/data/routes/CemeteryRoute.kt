package com.cemetery.data.routes

import com.cemetery.data.getAllGravesForUser
import com.cemetery.data.getCemeteriesForUser
import com.cemetery.data.insertNewCemeteries
import com.cemetery.data.insertNewGraves
import com.cemetery.data.requests.AddNewCemsRequest
import com.cemetery.data.requests.AddNewGravesRequest
import com.cemetery.data.responses.SimpleResponse
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.cemeteryRoute() {
    route("/getAllCems"){

        //whoever makes requests to this endpoint must be authenticated
        authenticate {
            get {
                val email: String? = call.principal<UserIdPrincipal>()?.name

                if(email.isNullOrEmpty()){
                    call.respond(BadRequest)
                }else{
                    val cemeteryList = getCemeteriesForUser()
                    call.respond(OK, cemeteryList)
                }

            }
        }
    }

    route("/getAllGraves"){
        authenticate {
            get {

                val email = call.principal<UserIdPrincipal>()?.name

                if(email.isNullOrEmpty()){
                    call.respond(BadRequest)
                }else{

                    val graveList = getAllGravesForUser()
                    call.respond(OK, graveList)
                }
            }
        }
    }

    route("/sendNewCems"){
        authenticate {
            post {
                val request = try {
                    call.receive<AddNewCemsRequest>()
                }catch (e : ContentTransformationException){
                    call.respond(BadRequest)
                    return@post
                }

                if(insertNewCemeteries(request.newCemList)){
                    call.respond(SimpleResponse( true,"Successfully added new cemeteries"))
                }else{
                    call.respond(SimpleResponse(false, "Failed to add new cemeteries"))
                }
            }
        }
    }

    route("/sendNewGraves"){
        authenticate {
            post {
                val request = try {
                    call.receive<AddNewGravesRequest>()
                }catch (e : ContentTransformationException){
                    call.respond(BadRequest)
                    return@post
                }

                if(insertNewGraves(request.newGraveList)){
                    call.respond(SimpleResponse(true, "Successfully added new graves"))
                }else {
                    call.respond(SimpleResponse(false, "Failed to add new graves"))
                }
            }
        }
    }


}