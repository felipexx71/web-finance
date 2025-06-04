package com.example.pjct.controller


import com.example.pjct.domain.dto.GastoPorCategoriaDTO
import com.example.pjct.domain.dto.ResumoFinancasDTO
import com.example.pjct.infra.ContaService
import com.example.pjct.infra.respository.TransacaoRepository
import com.example.pjct.infra.TransacaoService
import com.example.pjct.infra.UsuarioService
import com.example.pjct.infra.respository.ContaRepository
import com.example.pjct.infra.respository.UsuarioRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/financeiro")
class FinanceiroController(
    private val transacaoRepo: TransacaoRepository,
    private val transacaoService : TransacaoService,
    private val usuarioService: UsuarioService,
    private val usuarioRepositorio : UsuarioRepository,
    private val contaService: ContaService,
    private val contaRepositorio : ContaRepository
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
    ): RedirectView {
        transacaoService.adicionarTransacao(contaId
        ,categoriaId
        ,valor
        ,descricao
        ,tipo
        )
        return RedirectView("/page/home")
    }

    @PostMapping("/deletar_transacao")
    fun deletarTransacao(
        @RequestParam idTransacao: Long,
    ): RedirectView {
        transacaoRepo.deleteById(idTransacao)
        return RedirectView("/page/home")
    }

    @PostMapping("/adicionar_usuario")
    fun adicionarUsuario(
        @RequestParam nome : String,
        @RequestParam email : String
    ): RedirectView {
        usuarioService.adicionarUsuario(
            nome, email
        )
        return RedirectView("/page/home")
    }
    @PostMapping("/deletar_usuario")
    fun deletarUsuario(
        @RequestParam idUsuario: Long,
    ): RedirectView {
        usuarioRepositorio.deleteById(idUsuario)
        return RedirectView("/page/home")
    }

    @PostMapping("/adicionar_conta")
    fun adicionarConta(
        @RequestParam nome: String,
        @RequestParam saldo: BigDecimal = BigDecimal.ZERO,
        @RequestParam tipo: String,
        @RequestParam usuario_id : Long) : RedirectView {

        contaService.adicionarConta(nome, saldo, tipo, usuario_id)
        return RedirectView("/page/home")
    }
    @PostMapping("/deletar_conta")
    fun deletarConta(
        @RequestParam idConta: Long,
    ): RedirectView {
        contaRepositorio.deleteById(idConta)
        return RedirectView("/page/home")
    }

}