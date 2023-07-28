package com.fabioaacarneiro.contactlistk.services

import com.fabioaacarneiro.contactlistk.entities.Contact
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactCannotBeDeletedException
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactCannotInsertedException
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactCannotUpdatedException
import com.fabioaacarneiro.contactlistk.exceptions.contactExceptions.ContactNotFoundException
import com.fabioaacarneiro.contactlistk.repositories.ContactRepository
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.contracts.contract

@Service
class ContactService (val repository: ContactRepository) {

    fun findAll(): Iterable<Contact> {
        return repository.findAll()
    }

    fun findById(id: Long): Contact {
        val contact: Optional<Contact> = repository.findById(id)
        return contact.orElseThrow {
            ContactNotFoundException()
        }
    }

    fun findByName(name: String): Contact {
        val contact: Optional<Contact> = repository.findByName(name)
        return contact.orElseThrow {
            ContactNotFoundException()
        }
    }

    fun update(id: Long, people: Contact): Contact {
        val obj: Contact = findById(id)

        try {
            updatePeople(obj, people)
            return repository.save(obj)
        } catch (e: ContactCannotUpdatedException) {
            throw ContactCannotUpdatedException()
        }
    }

    private fun updatePeople(people: Contact, newPeople: Contact) {
        people.setName(newPeople.getName())
        people.setPhone(newPeople.getPhone())
        people.setAddress(newPeople.getAddress())
    }

    fun insert(constact: Contact): Contact {
        try {
            return repository.save(constact)
        } catch (e: ContactCannotInsertedException) {
            throw ContactCannotInsertedException()
        }
    }

    fun delete(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: ContactCannotBeDeletedException) {
            throw ContactCannotBeDeletedException()
        }
    }

}