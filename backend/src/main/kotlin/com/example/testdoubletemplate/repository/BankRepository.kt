package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.BankEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime
import java.util.*

interface BankRepository : JpaRepository<BankEntity, UUID>{
    fun findByTransactionDateBetween(startDateTime: OffsetDateTime, endDateTime: OffsetDateTime): List<BankEntity>
}