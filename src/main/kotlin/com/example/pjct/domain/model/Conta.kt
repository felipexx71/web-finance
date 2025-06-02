package com.example.pjct.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "contas", schema = "finance")
data class Conta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,
    val saldo: BigDecimal = BigDecimal.ZERO,
    val tipo: String,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    val usuario: Usuario,

    @Column(name = "data_criacao")
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)