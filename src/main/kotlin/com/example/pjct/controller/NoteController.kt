package com.example.pjct.controller

import com.example.pjct.domain.model.Notes
import com.example.pjct.infra.respository.NotesRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notes")
class NoteController (private val notesRepository : NotesRepository) {

    //create notes in BD
    @PostMapping
    fun create (@RequestBody note : Notes) : Notes {
        return notesRepository.save(note)
    }
    //return notes from BD
    @GetMapping
    fun list(): List<Notes> {
        return notesRepository.findAll()
    }
    //return notes from id
    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long): Notes {
        return notesRepository.findById(id).orElseThrow {
            RuntimeException("Note not found")
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : Long, @RequestBody updatedNotes: Notes): Notes {
        return notesRepository.findById(id).map { existingNote ->
            existingNote.copy(
                title = updatedNotes.title,
                description = updatedNotes.description
            ).also { notesRepository.save(it) }}.orElseThrow {
                RuntimeException("Note not found")
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Long) {
        notesRepository.deleteById(id)
    }


}