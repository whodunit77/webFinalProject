package db.project.ghorbanian.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.ResponseEntity
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime


/**
 * Created by mgh on Jan, 2020
 **/
/**
 * url: http://localhost:8080/swagger-ui.html#/
 */

@Configuration
@EnableSwagger2
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("db.project.ghorbanian"))
                .paths(regex("/rest.*"))
                .paths(regex("/*.*"))
                .build()
    }

//
}