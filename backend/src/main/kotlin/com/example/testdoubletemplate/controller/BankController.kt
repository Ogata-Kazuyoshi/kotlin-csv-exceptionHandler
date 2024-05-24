package com.example.testdoubletemplate.controller

import com.example.testdoubletemplate.advice.InvalidDateException
import com.example.testdoubletemplate.model.request.RequestCreateReports
import com.example.testdoubletemplate.model.response.ResponseCsvFile
import com.example.testdoubletemplate.service.BankService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

@RestController
@RequestMapping("/api/v1")
class BankController(val service:BankService) {

    @PostMapping("/users/reports")
    fun createReport(
        @RequestBody
        reqBody: RequestCreateReports
    ){
        val startAt = OffsetDateTime.parse(reqBody.start_at)
        val endAt = OffsetDateTime.parse(reqBody.end_at)
        if (endAt.isBefore(startAt)) {
            throw InvalidDateException()
//            throw InvalidDateException("入力された日付が正しくありません")
        }
        service.createReport(reqBody)
    }

    @GetMapping("/users/reports")
    @ResponseBody
    fun getReport():ResponseEntity<ResponseCsvFile>{
        val res = service.getReport()
        println("GetData : $res")
        return ResponseEntity.ok(res)
    }
}



//Method: POST
//Path: /api/bank/reports
//Request: {
//    {
//        "start_at": "2024-01-01T00:00:00+00:00",
//        "end_at": "2024-01-03T00:00:00+00:00"
//    }
//}