package com.example.pjct.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "categorias", schema = "finance")
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @Enumerated(EnumType.STRING)
    val tipo: TipoCategoria
)

enum class TipoCategoria {
    RECEITA,
    DESPESA
}