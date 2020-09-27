package com.cemetery

import com.cemetery.data.checkPasswordForEmail
import com.cemetery.data.routes.cemeteryRoute
import com.cemetery.data.routes.loginRoute
import com.cemetery.data.routes.registerRoute
import io.ktor.application.*
import io.ktor.auth.*
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

    install(Authentication){
        configureAuth()
    }
    install(Routing) {
        registerRoute()
        loginRoute()
        cemeteryRoute()

    } //enables url endpoints to make this REST api

    //Specifies what type of data ktor server will serve
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }

}

private fun Authentication.Configuration.configureAuth() {
    basic {
        realm = "Cemetery Server"

        validate {credentials ->
            val email = credentials.name
            val password = credentials.password

            if(checkPasswordForEmail(email, password)){
                UserIdPrincipal(email)
            }else null

        }
    }
}
