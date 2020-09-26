package com.cemetery.data.routes

import com.cemetery.data.checkIfUserExists
import com.cemetery.data.collections.User
import com.cemetery.data.registerUser
import com.cemetery.data.requests.AccountRequest
import com.cemetery.data.responses.SimpleResponse
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.registerRoute() {
    route("/register"){
        post {

            val request = try {
                call.receive<AccountRequest>()
            }catch (e : ContentTransformationException){
                call.respond(BadRequest)
                return@post
            }

            val userExists = checkIfUserExists(request.email)
            if(!userExists){
                if(registerUser(User(request.email, request.password))){
                    call.respond(OK, SimpleResponse(true, "Successfully created account"))
                }else{
                    call.respond(OK, SimpleResponse(false, "An unknown error occurred"))
                }
            }else {
                call.respond(OK, SimpleResponse(false, "User with that email already exists"))
            }
        }
    }
}