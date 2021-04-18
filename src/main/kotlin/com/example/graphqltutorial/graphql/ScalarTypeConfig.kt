package com.example.graphqltutorial.graphql

import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal

@Configuration
internal class ScalarTypeConfig {
    @Bean
    fun currencyScalarType(): GraphQLScalarType {
        return GraphQLScalarType("PLNCurrency", "String representing PLN monetary value - decimal with 2 places like 13.42",
            object : Coercing<BigDecimal, String> {

                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return if (dataFetcherResult is BigDecimal) {
                        dataFetcherResult.toDouble().toString()
                    } else throw CoercingSerializeException(
                        "Error occurred on serializing class ")
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): BigDecimal {
                    try {
                        return parse(input)
                    }
                    catch (e: Exception){
                        throw CoercingParseValueException("Bad request")
                    }
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): BigDecimal {
                    try {
                        return parse(input)
                    }
                    catch (e: Exception){
                        throw CoercingParseLiteralException("Bad request")
                    }
                }

                protected fun parse(input: Any?): BigDecimal {
                    return BigDecimal(extractStringValue(input))
                }

                 private fun extractStringValue(input: Any?) : String?{
                     return when (input) {
                         is StringValue -> {
                             input.value
                         }
                         is String -> {
                             input
                         }
                         else -> {
                             null
                         }
                     }
                }
            })
    }
}
