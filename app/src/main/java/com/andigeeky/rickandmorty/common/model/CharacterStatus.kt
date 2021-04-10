package com.andigeeky.rickandmorty.common.model

enum class CharacterStatus(val key: String?) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");

    companion object {
        fun get(status: String?): CharacterStatus {
            return values().find { it.key == status } ?: UNKNOWN
        }
    }
}
