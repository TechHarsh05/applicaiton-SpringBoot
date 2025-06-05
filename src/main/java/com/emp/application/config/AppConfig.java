package com.emp.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder()
//            .username("harsh@gmail.com")
//            .password(passwordEncoder().encode("devil"))
//            .roles("ADMIN")
//            .build();
//        UserDetails userDetails2 = User.builder()
//        		.username("suprav@gmail.com")
//        		.password(passwordEncoder().encode("sup"))
//        		.roles("Admin")
//        		.build();
//        return new InMemoryUserDetailsManager(userDetails, userDetails2);
//    }
	
	
	  @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
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
