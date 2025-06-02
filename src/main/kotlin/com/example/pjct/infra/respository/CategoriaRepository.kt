package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepository : JpaRepository<Categoria,Long> {
}