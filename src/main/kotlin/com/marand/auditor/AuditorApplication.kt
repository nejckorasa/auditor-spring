package com.marand.auditor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


/**
 * @author Nejc Korasa
 */

@SpringBootApplication
open class AuditorApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(AuditorApplication::class.java, *args)
        }
    }
}
