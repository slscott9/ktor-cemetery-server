package com.cemetery.data.responses

/*
    outgoing back to client
 */
data class SimpleResponse(
        val successful : Boolean,
        val message : String
) {
}