package com.elnfach.arthouse.profile

import com.elnfach.model.User

data class ProfileState(
    val value: User? = null,
    val error: String = "",
    val loading: Boolean = false
)
