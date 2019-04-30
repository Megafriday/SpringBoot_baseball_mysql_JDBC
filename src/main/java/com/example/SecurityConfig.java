package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	private static final String USER_SQL =
			"select username, password, enabled " +
					"from user " +
					"where username = ?";

	private static final String ROLE_SQL =
			"select username, authorities " +
					"from user " +
					"where username = ?";

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin()
		.loginPage("/login")
		.failureUrl("/login-error")
		.defaultSuccessUrl("/players")
		.permitAll();

		http.authorizeRequests()
		.antMatchers("/webjars/**", "/css/**", "/images/**", "/js/**").permitAll()
		.anyRequest().authenticated();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(USER_SQL)
		.authoritiesByUsernameQuery(ROLE_SQL)
		.passwordEncoder(createPasswordEncoder());

	}

}
