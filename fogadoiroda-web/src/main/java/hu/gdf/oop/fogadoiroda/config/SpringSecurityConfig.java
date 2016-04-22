package hu.gdf.oop.fogadoiroda.config;

import hu.gdf.oop.fogadoiroda.security.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    /**
     * Ebben a metódusban lehet a keretrendszer számára bedefiniálni, hogy melyik komponens végzi
     * az authentikáció számára a loginnév alapján a megfelelő felhasználó entitás kikeresését.
     *
     * @param registry authentikációért felelős manager komponens összeszereléséhez szükséges registry
     * @throws Exception sikertelen keresés esetén
     */
    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        registry.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ws/**");
    }

    /**
     * A teljes jogosultság konfigurációja.
     *
     * @param http Spring Security komponens, ezt lehet testreszabni
     * @throws Exception hiba esetén
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Cross-site Request Forgery ellen védekezés
        http.csrf();

        // URL pattern alapú authorizációs szabályok
        http.authorizeRequests().antMatchers("/css/**", "/fonts/**", "/js/**").permitAll();
        http.authorizeRequests().antMatchers("/sign-up", "/register").anonymous();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("OPERATOR");
        http.authorizeRequests().antMatchers("/users").hasRole("OPERATOR");
        http.authorizeRequests().antMatchers("/users/**").hasRole("OPERATOR");
        http.authorizeRequests().antMatchers("/event/**").hasRole("OPERATOR");
        http.authorizeRequests().antMatchers("/bets/**").hasRole("USER");
        http.authorizeRequests().anyRequest().authenticated();

        // belépési oldal beállításai
        http.formLogin().loginPage("/login");
        http.formLogin().loginProcessingUrl("/authenticate");
        http.formLogin().defaultSuccessUrl("/welcome", true);
        http.formLogin().failureUrl("/login?error=1").permitAll();

        // kilépés beállításai
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        // authorizációs hiba esetén ide küldjük a klienst
        http.exceptionHandling().accessDeniedPage("/access-denied");
    }

    /**
     * Az authentikáció számára a loginnév alapján a megfelelő felhasználó entitás kikeresését
     * végző komponens.
     * @return a user details service komponens
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = new MyUserDetailsServiceImpl();
        return userDetailsService;
    }
}
