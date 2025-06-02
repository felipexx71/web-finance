package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Transacao
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal
import java.time.LocalDate

@Service
class TransacaoService(
    private val transacaoRepository: TransacaoRepository,
    private val contaRepository: ContaRepository,
    private val categoriaRepository: CategoriaRepository
) {

    fun adicionarTransacao(
        contaId: Long,
        categoriaId: Long,
        valor: BigDecimal,
        descricao: String,
        tipo : String
    ): Transacao {
        val conta = contaRepository.findById(contaId)
            .orElseThrow { IllegalArgumentException("Conta não encontrada") }

        val categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow { IllegalArgumentException("Categoria não encontrada") }

        val transacao = Transacao(
            conta = conta,
            categoria = categoria,
            valor = valor,
            descricao = descricao,
            tipo = tipo
        )

        return transacaoRepository.save(transacao)
    }
}
