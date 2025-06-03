package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Transacao
import com.example.pjct.domain.dto.GastoPorCategoriaDTO
import com.example.pjct.domain.dto.ResumoFinancasDTO
import com.example.pjct.domain.dto.TransacoesAllDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TransacaoRepository : JpaRepository<Transacao, Long> {

    @Query("""
    SELECT new com.example.pjct.domain.dto.ResumoFinancasDTO(
        COALESCE(SUM(CASE WHEN t.tipo = 'RECEITA' THEN t.valor ELSE 0 END), 0),
        COALESCE(SUM(CASE WHEN t.tipo = 'DESPESA' THEN ABS(t.valor) ELSE 0 END), 0),
        COALESCE(SUM(t.valor), 0)
    )
    FROM Transacao t
    WHERE t.conta.usuario.id = :usuarioId
    AND t.data BETWEEN :startDate AND :endDate
""")
    fun getResumoPeriodo(
        @Param("usuarioId") usuarioId: Long,
        @Param("startDate") startDate: LocalDate,
        @Param("endDate") endDate: LocalDate
    ): ResumoFinancasDTO

    @Query("""
        SELECT new com.example.pjct.domain.dto.GastoPorCategoriaDTO(
            c.nome,
            SUM(ABS(t.valor))
        )
        FROM Transacao t
        JOIN t.categoria c
        WHERE t.conta.usuario.id = :usuarioId
        AND t.tipo = 'DESPESA'
        AND t.data BETWEEN :startDate AND :endDate
        GROUP BY c.nome
        ORDER BY SUM(ABS(t.valor)) DESC
    """)
    fun getGastosPorCategoria(
        @Param("usuarioId") usuarioId: Long,
        @Param("startDate") startDate: LocalDate,
        @Param("endDate") endDate: LocalDate
    ): List<GastoPorCategoriaDTO>

    @Query("""
    SELECT new com.example.pjct.domain.dto.TransacoesAllDTO(
        t.id,
        t.descricao,
        t.valor,
        t.tipo,
        t.data,
        t.categoria.nome,
        t.conta.nome
    )
    FROM Transacao t
""")
    fun getTransacoesAll(): List<TransacoesAllDTO>
}