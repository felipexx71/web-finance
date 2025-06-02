package com.example.pjct.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "transacoes", schema = "finance")
data class Transacao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val valor: BigDecimal,
    val data: LocalDate = LocalDate.now(),
    val descricao: String,
    val tipo: String,

    @ManyToOne
    @JoinColumn(name = "conta_id")
    val conta: Conta,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    val categoria: Categoria? = null,

    @Column(name = "data_criacao")
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)