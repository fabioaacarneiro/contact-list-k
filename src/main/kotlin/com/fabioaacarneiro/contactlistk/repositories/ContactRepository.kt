package com.fabioaacarneiro.contactlistk.repositories

import com.fabioaacarneiro.contactlistk.entities.Contact
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ContactRepository : CrudRepository<Contact, Long> {
    fun findByName(name: String): Optional<Contact>
}