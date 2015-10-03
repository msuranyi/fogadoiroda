package hu.gdf.oop.fogadoiroda.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		registry.inMemoryAuthentication().withUser("user").password("a").roles("USER");
		registry.inMemoryAuthentication().withUser("operator").password("a").roles("USER", "OPERATOR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/css/**", "/fonts/**", "/js/**").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("OPERATOR");
		http.authorizeRequests().anyRequest().authenticated();

		http.formLogin().loginPage("/login");
		http.formLogin().loginProcessingUrl("/authenticate");
		http.formLogin().defaultSuccessUrl("/welcome", true);
		http.formLogin().failureUrl("/login?error=1").permitAll();

		http.logout().logoutUrl("/logout");

		http.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
