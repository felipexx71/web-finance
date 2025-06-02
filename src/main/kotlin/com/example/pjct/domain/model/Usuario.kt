package com.example.pjct.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios", schema = "finance")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @Column(unique = true)
    val email: String,

    @Column(name = "data_criacao")
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)