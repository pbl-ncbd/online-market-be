package com.example.onlinemarketbe.security.jwt;



import com.example.onlinemarketbe.services.impl.CustomUserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final  JwtUtils jwtUtils;

    private final  CustomUserDetailsService userDetailsService;

    AuthTokenFilter(JwtUtils jwtUtils,
                    CustomUserDetailsService userDetailsService){
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    private static final Logger logger = LoggerFactory
                                          .getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
      try {
        String jwt = getJwtFromRequest(request);
        logger.info("Auth token filter: jwt = " + jwt);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
          String username = jwtUtils.getUserNameFromJwtToken(jwt);
          UserDetails userDetails = userDetailsService
                                    .loadUserByUsername(username);

          UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(userDetails,
                          null,
                          userDetails.getAuthorities());

          authentication.setDetails(new WebAuthenticationDetailsSource()
                                        .buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (Exception e) {
        logger.error("Cannot set user authentication: {}", e);
      }

      filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      logger.info("Auth token filter: bearerToken = " + bearerToken);
      if (bearerToken == null) {
        return  parseJwt(request);
      }
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
          return bearerToken.substring(7, bearerToken.length());
      }
      if (StringUtils.hasText(bearerToken)) {
        return bearerToken;
      }
      return null;
    }
  }


