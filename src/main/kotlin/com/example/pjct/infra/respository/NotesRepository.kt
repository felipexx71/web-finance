package com.example.pjct.infra.respository

import com.example.pjct.domain.model.Notes
import org.springframework.data.jpa.repository.JpaRepository

interface NotesRepository : JpaRepository<Notes,Long> {
}