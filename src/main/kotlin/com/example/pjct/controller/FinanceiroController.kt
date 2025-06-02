package com.example.pjct.controller


import com.example.pjct.domain.dto.GastoPorCategoriaDTO
import com.example.pjct.domain.dto.ResumoFinancasDTO
import com.example.pjct.infra.respository.TransacaoRepository
import com.example.pjct.infra.respository.TransacaoService
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/api/financeiro")
class FinanceiroController(
    private val transacaoRepo: TransacaoRepository,
    private val transacaoService : TransacaoService
) {

    @GetMapping("/resumo/{usuarioId}")
    fun getResumo(
        @PathVariable usuarioId: Long,
        @RequestParam startDate: String,
        @RequestParam endDate: String
    ): ResumoFinancasDTO {
        return transacaoRepo.getResumoPeriodo(
            usuarioId,
            LocalDate.parse(startDate),
            LocalDate.parse(endDate)
        )
    }

    @GetMapping("/categorias/{usuarioId}")
    fun getGastosPorCategoria(
        @PathVariable usuarioId: Long,
        @RequestParam startDate: String,
        @RequestParam endDate: String
    ): List<GastoPorCategoriaDTO> {
        return transacaoRepo.getGastosPorCategoria(usuarioId,
            LocalDate.parse(startDate),
            LocalDate.parse(endDate)
        )
    }

    @PostMapping("/adicionar_transacao")
    fun adicionarTransacao(
        @RequestParam contaId: Long,
        @RequestParam categoriaId : Long,
        @RequestParam valor : BigDecimal,
        @RequestParam descricao : String,
        @RequestParam tipo : String
    ) {
        transacaoService.adicionarTransacao(contaId
        ,categoriaId
        ,valor
        ,descricao
        ,tipo
        )
    }
}