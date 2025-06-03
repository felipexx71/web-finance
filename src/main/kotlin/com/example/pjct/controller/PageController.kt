package com.example.pjct.controller

import com.example.pjct.domain.dto.GastoPorCategoriaDTO
import com.example.pjct.infra.respository.TransacaoRepository
import com.example.pjct.infra.respository.TransacaoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page")
class PageController(private val transacaoRepo: TransacaoRepository) {
        @GetMapping("/home")
    fun home(model: Model): String {
        val transacoes = transacaoRepo.findAll()
        model.addAttribute("transacoes", transacoes)
        return "home"
    }
}