package com.myblog.congif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


//SecurityConfig =which url can who access which one and what kind of authentication,we are  securing the url

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return  super.authenticationManagerBean();
    }
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Override
   protected void configure(HttpSecurity http) throws Exception{
        LogoutSuccessHandler logoutSuccessHandler;
        http
               .csrf().disable().
               authorizeRequests()
               .antMatchers(HttpMethod.GET,"/api/**").permitAll()
               .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
               .anyRequest()
               .authenticated()
               .and()
                .httpBasic();
//               .and()
//                .logout()
//                .logoutUrl("/api/auth/logout") // Specify the URL for logout
              // .logoutSuccessHandler(logoutSuccessHandler)
//               .permitAll();
              //  .deleteCookies("JSESSIONID")
   }

   @Override
    @Bean
    protected UserDetailsService userDetailsService(){
       UserDetails user=User.builder().username("pankaj").password(passwordEncoder().encode("password")).roles("USER").build();
       UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
   }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());//takes the sigin data and also the datatbase data
    }


}


//csrf token are used for the security purposes which websites from making unauthorised request on behalf of an authenticated user..
//authorizeRequests= its the entry point  for the defining access control rules.
// any request means all yor url should be accessed