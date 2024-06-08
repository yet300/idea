package com.ideaapp.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform