package com.example.testdoubletemplate.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseCsvFile(
    @JsonProperty("csv_data")
    val csv_Data: MutableList<Array<String>>
)

