package com.fabioaacarneiro.contactlistk.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_contacts")
class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,
    private var name: String,
    private var phone: String,
    private var address: String
) {
    fun getId() = id
    fun getName() = name
    fun getPhone() = phone
    fun getAddress() = address

    fun setName(name: String) {
        this.name = name
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun  setAddress(address: String) {
        this.address = address
    }
}