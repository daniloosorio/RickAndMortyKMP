package org.example.richandmorty

expect fun getCurrentTarget():Target

enum class Target {
    iOs, Android, Desktop
}

fun isDesktop() = getCurrentTarget() == Target.Desktop