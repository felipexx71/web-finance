package com.example.pjct.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "notes", schema = "finance")
data class Notes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String,
    val description: String
)