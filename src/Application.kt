package com.cemetery

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders) //append day to responses from the ktor server
    install(CallLogging) //log incoming and outgoing http requests
    install(Routing) //enables url endpoints to make this REST api

    //Specifies what type of data ktor server will serve
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }
}

