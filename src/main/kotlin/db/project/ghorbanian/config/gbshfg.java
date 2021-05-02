package db.project.ghorbanian.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Just return a string representing the username
        return Optional.ofNullable("Kindson").filter(s -> !s.isEmpty());
    }

}