//package com.example.PersonalFinanceManagementSystem.security;
//
//import com.example.PersonalFinanceManagementSystem.service.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Fetching authentication header
//        var jwtAuthTokenOptional = getTokenFromRequest(request);
//
//        // Validate JWT Token -> JWT utils
//        jwtAuthTokenOptional.ifPresent(jwtToken -> {
//            if(jwtTokenProvider.validateToken(jwtToken)) {
//                // Get username from JWT Token
//                String username = jwtTokenProvider.getUsernameFromToken(jwtToken);
//
//                // Fetch User details with the help of username
//                var userDetails = userService.getUserByUsername(username);
//
//                // Create Authentication Token
//                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getRoles());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                // Set Authentication Token to Security Context
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        });
//
//        // Pass request and response to next filter
//        filterChain.doFilter(request, response);
//    }
//
//    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
//        // Extract authentication header
//        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        // Bearer <JWT TOKEN>
//        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
//            return Optional.of(authHeader.substring(7));
//        }
//
//        return Optional.empty();
//    }
//}
//
