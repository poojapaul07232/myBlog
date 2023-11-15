package com.myblog.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component  // This annotation marks the class as a Spring component, making it eligible for automatic component
           // scanning and instantiation. In this context, it's used to ensure that the JwtAuthenticationEntryPoint
           // is created and managed by the Spring application context.
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request
                        , HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException
                    {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
                    }
}

//commence= this method validate the token

//JwtAuthenticationEntryPoint class: =This is a custom class that handles how the application responds when
   // an unauthenticated request is made to a secured resource. It implements the AuthenticationEntryPoint interface,
   // which requires overriding the commence method(validating the token and send a response with an exception when token is not invalid).

// commence method: This is the method required by the AuthenticationEntryPoint interface, and it is
    //  called when an unauthenticated user tries to access a protected resource.