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
import java.util.regex.Pattern

@Configuration
internal class ScalarTypeConfig {

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    )

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
                    return parse(input)
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): BigDecimal {
                    return parse(input)
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



    @Bean
    fun emailScalarType(): GraphQLScalarType {
        return GraphQLScalarType("Email", "String representing email",
            object : Coercing<String, String> {

                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return if (dataFetcherResult is String && isValidEmail(dataFetcherResult)) {
                        dataFetcherResult
                    } else throw CoercingSerializeException(
                        "Error occurred on serializing class ")
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): String {
                    try {
                        return parse(input)
                    }
                    catch (e: Exception){
                        throw CoercingParseValueException("Bad request")
                    }
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): String {
                    try {
                        return parse(input)
                    }
                    catch (e: Exception){
                        throw CoercingParseLiteralException("Bad request")
                    }
                }

                protected fun parse(input: Any?): String {
                    if( isValidEmail(extractStringValue(input))) {
                        return extractStringValue(input)
                    }
                    throw java.lang.Exception()
                }

                private fun extractStringValue(input: Any?) : String{
                    return when (input) {
                        is StringValue -> {
                            input.value
                        }
                        is String -> {
                            input
                        }
                        else -> {
                            throw java.lang.Exception()
                        }
                    }
                }
            })
    }


        fun isValidEmail(target: CharSequence?): Boolean {
        return if (target.isNullOrBlank()) {
            false
        } else {
            EMAIL_ADDRESS_PATTERN.matcher(target).matches()
        }
    }
}
