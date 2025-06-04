package com.example.pjct.infra

import com.example.pjct.domain.model.Conta
import com.example.pjct.infra.respository.ContaRepository
import com.example.pjct.infra.respository.UsuarioRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ContaService (
    private val contaService: ContaRepository,
    private val usuarioRepository: UsuarioRepository
)  {

    fun adicionarConta(
        nome: String,
        saldo: BigDecimal = BigDecimal.ZERO,
        tipo: String,
        usuario_id : Long) : Conta {

        val usuario = usuarioRepository.findById(usuario_id)
            .orElseThrow { IllegalArgumentException("Esse usuário não existe!") }

        val conta = Conta (
            nome = nome,
            saldo = saldo,
            tipo = tipo,
            usuario = usuario
        )

        return contaService.save(conta)
    }
}