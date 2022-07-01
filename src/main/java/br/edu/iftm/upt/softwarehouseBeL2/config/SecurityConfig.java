package br.edu.iftm.upt.softwarehouseBeL2.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// Qualquer um pode fazer requisições para essas URLs
				.antMatchers("/css/**", "/js/**", "/", "/index.html").permitAll()
				// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
				.antMatchers("/home/**").hasAnyRole("ADMIN","USUARIO")
				//.antMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
				.and()
			// A autenticação usando formulário está habilitada 
			.formLogin()
				// Uma página de login customizada
				.loginPage("/login")
				// Define a URL para o caso de falha no login
				//.failureUrl("/login-error");
				.and()
				// Para fazer logout (já é configurado automaticamente para a URL /logout)
				// Só está habilitado aqui para mudarmos a URL de sucesso do logout
				.logout()
				// Define a URL para o caso do usuário fazer logout. O padrão é /login
				.logoutSuccessUrl("/");
	}
	
	//Autenticacao JDBC
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("select emailusuario, senhausuario, ativousuario from usuario where emailusuario = ?")
	.authoritiesByUsernameQuery("SELECT tab.emailusuario, papel.nomepapel from" + 
		"(SELECT usuario.emailusuario, usuario.idusuario from usuario where emailusuario = ? ) as tab" + 
		" inner join usuario_papel on codigo_usuario = tab.idusuario \n" + 
		" inner join papel on codigo_papel = papel.idpapel;")
	.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		String idEnconder = "argon2";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("argon2", new Argon2PasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idEnconder, encoders);
		 
		return passwordEncoder;
	}
	
	//Autenticacao em memória
	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth
			//.inMemoryAuthentication()
				//.withUser("leo1").password("{noop}12345").roles("ADMIN")
				//.and()
				//.withUser("leo2").password("{noop}12345").roles("USUARIO");
				//.and()
				//.withUser("outro").password("12345").roles("ADMIN", "USUARIO");
}
