package zw.co.jugaad.presenter.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zw.co.jugaad.presenter.usecases.security.CustomUserDetailsService;
import zw.co.jugaad.presenter.usecases.security.JwtAuthenticationEntryPoint;
import zw.co.jugaad.presenter.usecases.security.JwtAuthenticationFilter;
import zw.co.jugaad.presenter.usecases.security.JwtProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig  {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomUserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, customUserDetailsService);
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .cors().disable()
            .csrf().disable()
            .logout().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
                .headers().frameOptions().sameOrigin()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeHttpRequests()
                .anyRequest().permitAll();
//                .antMatchers("/Customer/**").permitAll()
//                .antMatchers("/Cousine/**").permitAll()
//                .antMatchers("/Store/**").permitAll()
//                .antMatchers("/Product/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/Order/**").permitAll()
//                .anyRequest().authenticated();

        // @formatter:on

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
