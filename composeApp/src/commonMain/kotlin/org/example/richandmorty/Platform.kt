package org.example.richandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform