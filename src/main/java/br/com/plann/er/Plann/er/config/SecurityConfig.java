package br.com.plann.er.Plann.er.config;

import br.com.plann.er.Plann.er.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST, "/user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/redeem-password").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/reset-password").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/convidados/{viagemId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/viagem/create").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/viagem/update/{viagemId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/link/create/{viagemId}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/link/{viagemId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/viagens/{viagemId}/atividades/gerar").permitAll()
                        //ARRUMAR OS ROLES E TIRAR DO PERMIT ALL
                ).oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtConfig.jwtDecoder())));

        return http.build();
    }
}
