package com.m01n008.minirevenuecatsdk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform