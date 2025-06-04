package cl.Ferramas.Ferramas.config;

import cl.Ferramas.Ferramas.security.CustomUserDetailsService;
import cl.Ferramas.Ferramas.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers("/auth/login", "/usuario", "/registro", "/public/**").permitAll()

                // Cliente
                .requestMatchers(
                    "/cliente/**", "/catalogo/**", "/producto/**", "/carrito/**", "/compra/**",
                    "/pedido", "/pedido/**", "/pago", "/pago/**", "/detallepedido/**"
                ).hasAnyRole("CLIENTE", "ADMIN")

                // Vendedor
                .requestMatchers(
                    "/pedido/**", "/detallepedido/**", "/producto/**", "/usuario/usuarioporrol/**",
                    "/usuario/usuarioporsucursal/**", "/usuario", "/usuario/**"
                ).hasAnyRole("VENDEDOR", "ADMIN")

                // Bodeguero
                .requestMatchers(
                    "/inventario/**", "/movimientoinventario/**", "/producto/**", "/marca/**",
                    "/categoria/**", "/tipomov/**"
                ).hasAnyRole("BODEGUERO", "ADMIN")

                // Despachador
                .requestMatchers(
                    "/despacho/**", "/estadodespacho/**"
                ).hasAnyRole("DESPACHADOR", "ADMIN")

                // Reportes solo para Admin
                .requestMatchers("/reporte/**").hasRole("ADMIN")

                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
