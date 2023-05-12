package com.example.fakestoreapi.utils

private val hashMap = mapOf(
    "johnd" to 1,
    "mor_2314" to 2,
    "kevinryan" to 3,
    "donero" to 4,
    "derek" to 5,
    "david_r" to 6,
    "snyder" to 7,
    "hopkins" to 8,
    "kate_h" to 9,
    "jimmie_k" to 10
)

fun userNameToId(userName: String): Int {
    return hashMap[userName] ?: -2
}