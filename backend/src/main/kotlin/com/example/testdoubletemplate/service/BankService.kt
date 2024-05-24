package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.request.RequestCreateReports
import com.example.testdoubletemplate.model.response.ResponseCsvFile
import com.example.testdoubletemplate.repository.BankRepository
import com.opencsv.CSVReader
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileReader
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


interface BankService {
    fun createReport(reqBody: RequestCreateReports):Unit
    fun getReport(): ResponseCsvFile
}

@Service
class BankServiceImpl(
    val bankRepository:BankRepository
) : BankService {

    override fun createReport(reqBody:RequestCreateReports) {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val startDateTime = OffsetDateTime.parse(reqBody.start_at, formatter)
        val endDateTime = OffsetDateTime.parse(reqBody.end_at, formatter)

        val transactionDataList = bankRepository.findByTransactionDateBetween(startDateTime, endDateTime)
        val data = mutableListOf(
            listOf("id", "account_id", "transactionAmaount", "transactionDate"),
        )
        transactionDataList.map { transactionData ->
            val detailData = mutableListOf<String>()
            detailData.add(transactionData.id.toString())
            detailData.add(transactionData.account_id)
            detailData.add(transactionData.transactionAmaount.toString())
            detailData.add(transactionData.transactionDate.toString())
            data.add(detailData)
        }

        val file = File("./backend/src/main/kotlin/com/example/testdoubletemplate/data/transaction.csv")
        file.parentFile.mkdirs()  // ディレクトリが存在しない場合にディレクトリを作成
        file.printWriter().use { out ->
            data.forEach { row ->
                out.println(row.joinToString(","))
            }
        }
    }

    override fun getReport(): ResponseCsvFile {
        val fileName = "./backend/src/main/kotlin/com/example/testdoubletemplate/data/transaction.csv"
        val reader = CSVReader(FileReader(fileName))
        val allRows = reader.readAll()
        val responseCsvFile = ResponseCsvFile(
            csv_Data = mutableListOf()
        )
        allRows.forEach { row ->
            responseCsvFile.csv_Data.add(row)
        }
        return responseCsvFile
    }
}