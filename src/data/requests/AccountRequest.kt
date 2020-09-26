package com.cemetery.data.requests


/*
    Incoming from android app for validation of user
 */
data class AccountRequest(
        val email: String,
        val password : String
) {
}