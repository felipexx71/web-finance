package com.example.pjct.controller

import com.example.pjct.domain.dto.GastoPorCategoriaDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page")
class PageController {

    @GetMapping("/home")
    fun home() : String {
        return  "home"
    }
}