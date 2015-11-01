package hu.gdf.oop.fogadoiroda.config;

import hu.gdf.oop.fogadoiroda.security.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring-Security framework (jogosultság kezeléshez) felépítése az alkalmazás
 * indulásakor.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        registry.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf();

        http.authorizeRequests().antMatchers("/css/**", "/fonts/**", "/js/**").permitAll();
        http.authorizeRequests().antMatchers("/sign-up", "/register").anonymous();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("OPERATOR");
        http.authorizeRequests().antMatchers("/users").hasRole("OPERATOR");
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin().loginPage("/login");
        http.formLogin().loginProcessingUrl("/authenticate");
        http.formLogin().defaultSuccessUrl("/welcome", true);
        http.formLogin().failureUrl("/login?error=1").permitAll();

//		http.logout().logoutUrl("/logout");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = new MyUserDetailsServiceImpl();
        return userDetailsService;
    }
}
