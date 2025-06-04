package com.example.pjct.infra

import com.example.pjct.domain.model.Transacao
import com.example.pjct.infra.respository.CategoriaRepository
import com.example.pjct.infra.respository.ContaRepository
import com.example.pjct.infra.respository.TransacaoRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

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
