package com.example.onlinemarketbe.config;
import com.example.onlinemarketbe.security.jwt.AuthEntryPointJwt;
import com.example.onlinemarketbe.security.jwt.AuthTokenFilter;
import com.example.onlinemarketbe.services.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthEntryPointJwt unauthorizedHandler;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthTokenFilter authTokenFilter;

    SecurityConfig(AuthEntryPointJwt unauthorizedHandler,
                   CustomUserDetailsService customUserDetailsService,
                   AuthTokenFilter authTokenFilter){
        this.customUserDetailsService = customUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authTokenFilter = authTokenFilter;

    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




    @Override
    protected void configure(final AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**"
        // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()  // whitelist Swagger UI resources
                    // ... here goes your custom security configuration
//                    .antMatchers("/**").authenticated()  // require authentication for any endpoint that's not whitelisted
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated();

        http.addFilterBefore(authTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}
