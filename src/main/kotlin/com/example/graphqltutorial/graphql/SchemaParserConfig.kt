package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.SchemaParserDictionary
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserNotFound
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SchemaParserConfig {
    @Bean
    fun schemaParserDictionary(): SchemaParserDictionary {
        return SchemaParserDictionary()
            .add(UserNotFound::class)
            .add(User::class)
    }
}
