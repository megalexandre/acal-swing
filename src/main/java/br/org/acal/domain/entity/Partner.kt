package br.org.acal.domain.entity

import lombok.Builder

@Builder
data class Partner (
    val id: String,
    val specialInvoice: Boolean,
    val patternOnly: Boolean,
    val person: Person,
    val number: String,
)