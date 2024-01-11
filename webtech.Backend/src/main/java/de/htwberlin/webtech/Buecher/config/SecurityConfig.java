/*package de.htwberlin.webtech.Buecher.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt();
    }
}
*/