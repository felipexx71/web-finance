package com.example.pjct.infra

import com.example.pjct.domain.model.Usuario
import com.example.pjct.infra.respository.UsuarioRepository
import org.springframework.stereotype.Service


@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun adicionarUsuario(
        nome: String,
        email: String,
    ): Usuario {
        val usuario = Usuario(
            nome = nome,
            email = email
        )

        if (usuarioRepository.existsByEmail(email)) {
            throw IllegalArgumentException("Já existe o email passado!")
        }
        if (!email.contains("@")) {
            throw IllegalArgumentException("O email deve ter pelo menos um @!")
        }
        if (!email.contains(".")) {
            throw IllegalArgumentException("O email deve ter pelo menos um .!")
        }
        if (email.length < 13) {
            throw IllegalArgumentException("O email deve ter pelo menos 12 caracteres!")
        }

        return usuarioRepository.save(usuario)
    }



}