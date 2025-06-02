package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Conta
import org.springframework.data.jpa.repository.JpaRepository

interface ContaRepository : JpaRepository<Conta, Long> {
}