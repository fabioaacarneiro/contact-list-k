package com.fabioaacarneiro.contactlistk.controllers

import com.fabioaacarneiro.contactlistk.entities.Contact
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactCannotBeDeletedException
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactCannotUpdatedException
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactNotFoundException
import com.fabioaacarneiro.contactlistk.services.ContactService
import jakarta.servlet.Servlet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/contacts")
class ContactController(val service: ContactService) {

    @GetMapping
    fun findAll(): ResponseEntity<Iterable<Contact>> {
        val contacts: Iterable<Contact> = service.findAll()
        return ResponseEntity.ok().body(contacts)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Contact> {
        val contact: Contact = service.findById(id)
        return ResponseEntity.ok().body(contact)
    }

    @PostMapping
    fun insert(@RequestBody contact: Contact): ResponseEntity<Contact> {
        val insertedContact: Contact = service.insert(contact)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(contact.getId())
            .toUri()

        return ResponseEntity.created(uri).body(insertedContact)
    }

    @PostMapping("/{name}")
    fun findByName(@PathVariable name: String): ResponseEntity<Contact> {
        val contact: Contact
        try {
            contact = service.findByName(name)
        } catch (e: ContactNotFoundException) {
            throw ContactNotFoundException()
        }
        return ResponseEntity.ok().body(contact)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        try {
            service.delete(id)
        } catch (e: ContactCannotBeDeletedException) {
            throw ContactCannotBeDeletedException()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody newContact: Contact): ResponseEntity<Contact> {
        val contact: Contact
        try {
            contact = service.update(id, newContact)
        } catch (e: ContactCannotUpdatedException) {
            throw ContactCannotUpdatedException()
        }
        return ResponseEntity.ok().body(contact)
    }
}