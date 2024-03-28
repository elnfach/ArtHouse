package com.elnfach.domain.repository.storage

interface IsAuthedUserStorage {
    fun save(status: String)
    fun get() : String
}