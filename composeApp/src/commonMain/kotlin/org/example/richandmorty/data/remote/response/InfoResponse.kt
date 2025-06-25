package org.example.richandmorty.data.remote.response

data class InfoResponse (
    val pages:Int,
    val next: String?,
    val prev: String
)