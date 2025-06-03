package com.example.pjct.domain.dto

import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDate

data class TransacoesAllDTO(
    val id: Long,
    val descricao: String,
    val valor: BigDecimal,
    val tipo: String,
    val data: LocalDate,
    val categoriaNome: String,
    val contaNome: String
)
