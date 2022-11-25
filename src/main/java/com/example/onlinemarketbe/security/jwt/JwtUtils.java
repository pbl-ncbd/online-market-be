package com.example.onlinemarketbe.security.jwt;

import com.example.onlinemarketbe.common.UserDetailsImpl;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings({"checkstyle:Indentation", "checkstyle:FileTabCharacter"})
@CrossOrigin()
@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


  @Value("${onlinemarketbe.app.jwtSecret}")
  private String jwtSecret;

  @Value("${onlinemarketbe.app.urlPath}")
  private String urlPath;
  @Value("${onlinemarketbe.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${onlinemarketbe.app.jwtCookieName}")
  private String jwtCookie;

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
    String jwt = generateTokenFromUsername(userPrincipal.getUsername());
    ResponseCookie cookie = ResponseCookie
                              .from(jwtCookie, jwt)
                              .path(urlPath)
                              .maxAge(86400)
                              .httpOnly(true).build();
    return cookie;
  }

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public ResponseCookie getCleanJwtCookie() {

    logger.info("Jwt utils clean");
    ResponseCookie cookie = ResponseCookie
                              .from(jwtCookie, null)
                              .path(urlPath).build();

    logger.info("Jwt utils clean cookie: " + cookie);
    return cookie;
  }


  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public String getUserNameFromJwtToken(String token) {
    return Jwts
            .parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts
          .parser()
          .setSigningKey(jwtSecret)
          .parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @deprecated Some javadoc.
   */
  public String generateTokenFromUsername(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }
}
