package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario,Long> {
    fun existsByEmail(email : String) : Boolean
}