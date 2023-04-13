package org.genspectrum.lapis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import mu.KotlinLogging
import org.genspectrum.lapis.auth.DataOpennessAuthorizationFilter
import org.genspectrum.lapis.config.DatabaseConfig
import org.genspectrum.lapis.config.SequenceFilterFields
import org.genspectrum.lapis.logging.RequestContext
import org.genspectrum.lapis.logging.RequestContextLogger
import org.genspectrum.lapis.logging.StatisticsLogObjectMapper
import org.genspectrum.lapis.util.TimeFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter
import java.io.File

@Configuration
class LapisSpringConfig {
    @Bean
    fun openAPI(sequenceFilterFields: SequenceFilterFields) = buildOpenApiSchema(sequenceFilterFields)

    @Bean
    fun databaseConfig(@Value("\${lapis.databaseConfig.path}") configPath: String): DatabaseConfig {
        return ObjectMapper(YAMLFactory()).registerKotlinModule().readValue(File(configPath))
    }

    @Bean
    fun sequenceFilterFields(databaseConfig: DatabaseConfig) = SequenceFilterFields.fromDatabaseConfig(databaseConfig)

    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(false)
        filter.setAfterMessagePrefix("REQUEST DATA: ")
        return filter
    }

    @Bean
    fun requestContextLogger(
        requestContext: RequestContext,
        statisticsLogObjectMapper: StatisticsLogObjectMapper,
        timeFactory: TimeFactory,
    ) = RequestContextLogger(
        requestContext,
        statisticsLogObjectMapper,
        KotlinLogging.logger("StatisticsLogger"),
        timeFactory,
    )

    @Bean
    fun dataOpennessAuthorizationFilter(databaseConfig: DatabaseConfig, objectMapper: ObjectMapper) =
        DataOpennessAuthorizationFilter.createFromConfig(databaseConfig, objectMapper)
}
