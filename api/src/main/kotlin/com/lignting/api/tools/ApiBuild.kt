package com.lignting.api.tools

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Component
class ApiBuild(private val requestMappingHandlerMapping: RequestMappingHandlerMapping): CommandLineRunner {
    private val logger = LoggerFactory.getLogger(ApiBuild::class.java)
    override fun run(vararg args: String?) {
        requestMappingHandlerMapping.handlerMethods.forEach {
            logger.info("info: ${it.key}")
            logger.info("method: ${it.value}")
        }
    }
}