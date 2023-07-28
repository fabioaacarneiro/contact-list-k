package com.fabioaacarneiro.contactlistk.exceptions

import java.util.Date

class MessageExceptionHandler (
    private val timestamp: Date,
    private val status: Int,
    private val errors: List<MessageException>
)