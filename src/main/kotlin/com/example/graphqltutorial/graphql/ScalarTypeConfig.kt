package com.example.graphqltutorial.graphql

import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Configuration
internal class ScalarTypeConfig {

    @Bean
    fun dateTimeScalarType(): GraphQLScalarType {
        return GraphQLScalarType("DateTime", "An RFC-3339 compliant date time scalar that accepts string values like 1996-12-19T16:39:57-08:00",
            object : Coercing<OffsetDateTime, String> {
                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return if (dataFetcherResult is OffsetDateTime) {
                        dataFetcherResult.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    } else throw CoercingSerializeException(
                        "Error occurred on serializing class ")
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): OffsetDateTime {
                    return parse(input)
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): OffsetDateTime {
                    return parse(input)
                }

                protected fun parse(input: Any?): OffsetDateTime {
                    return OffsetDateTime.parse(input as String?, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                }
            })
    }

    @Bean
    fun dateScalarType(): GraphQLScalarType {
        return GraphQLScalarType("Date", "An RFC-3339 compliant date scalar that accepts string values like 1996-12-19"
            ,
            object : Coercing<LocalDate, String> {
                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return if (dataFetcherResult is LocalDate) {
                        dataFetcherResult.format(DateTimeFormatter.ISO_DATE)
                    } else throw CoercingSerializeException(
                        "Error occurred on serializing class ")
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): LocalDate {
                    return parse(input)
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): LocalDate {
                    return parse(input)
                }

                protected fun parse(input: Any?): LocalDate {
                    return LocalDate.parse(input as String?, DateTimeFormatter.ISO_DATE)
                }
            })
    }
}
