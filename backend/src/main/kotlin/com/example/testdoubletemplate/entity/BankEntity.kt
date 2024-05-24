package com.example.testdoubletemplate.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.*


@Entity
@Table(name="BankTable")
data class BankEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val account_id: String = "",
    val transactionAmaount:Int = 0,
    val transactionDate:OffsetDateTime =OffsetDateTime.parse("2024-01-01T00:00:00+00:00"),
    val remark:String = "",
)
