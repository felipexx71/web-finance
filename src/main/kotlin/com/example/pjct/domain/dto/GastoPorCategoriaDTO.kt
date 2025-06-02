package com.example.pjct.domain.dto

import java.math.BigDecimal

data class GastoPorCategoriaDTO(
    val categoria: String,
    val total: BigDecimal
)