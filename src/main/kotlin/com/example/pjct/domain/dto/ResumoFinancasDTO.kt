package com.example.pjct.domain.dto

import java.math.BigDecimal

data class ResumoFinancasDTO(
    val totalReceitas: BigDecimal,
    val totalDespesas: BigDecimal,
    val saldo: BigDecimal
)
