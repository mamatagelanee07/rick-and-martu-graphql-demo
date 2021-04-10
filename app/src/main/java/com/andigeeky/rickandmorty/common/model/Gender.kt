package com.andigeeky.rickandmorty.common.model

enum class Gender(val gender: String) {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    companion object {
        fun get(gender: String?): Gender {
            return values().find { it.gender == gender } ?: UNKNOWN
        }
    }
}