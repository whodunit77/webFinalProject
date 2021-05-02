package db.project.ghorbanian

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


//@EnableJpaAuditing(auditorAwareRef="auditorAware")
@SpringBootApplication
class GhorbanianApplication


fun main(args: Array<String>) {
	runApplication<GhorbanianApplication>(*args)
}
