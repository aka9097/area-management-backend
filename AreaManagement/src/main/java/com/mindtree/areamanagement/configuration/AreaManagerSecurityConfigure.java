package com.mindtree.areamanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.mindtree.areamanagement.modules.user.service.UserService;

@Configuration
@EnableWebSecurity
public class AreaManagerSecurityConfigure extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth)
//      throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("email")
//          .password("password")
//          .roles("USER");
//    }
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}

    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          .antMatchers("/login").permitAll()
          .antMatchers("/addUser").permitAll()
          .antMatchers("/getUsers").permitAll()
          .antMatchers("/updateAdmin/{email},{newPassword}").permitAll()
          .antMatchers("/updateUserProfile/{email}").permitAll()
          .antMatchers("/addAddress").permitAll()
          .antMatchers("/allAddressDetails").permitAll()
          .antMatchers("/deleteAddress/{id}").permitAll()
          .antMatchers("/updateAddress/{id}").permitAll()
          .antMatchers("/raiseGenie").permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic();
    }

}
