package kr.ac.hs.oing.auth.infrastructure;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final int TOKEN_VALIDITY_TIME = 1000;
    private static final String CLAIMS_REGEX = ",";

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;


    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * TOKEN_VALIDITY_TIME;
    }

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(decodeBytes());
    }

    private byte[] decodeBytes() {
        return Decoders.BASE64.decode(secret);
    }

    public String createToken(Authentication authentication) {
        String authorities = authoritiesToString(authentication);
        long now = currentTime();
        Date validity = expireTime(now);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }


    private String authoritiesToString(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private long currentTime() {
        return (new Date()).getTime();
    }


    private Date expireTime(long now) {
        return new Date(now + this.tokenValidityInMilliseconds);
    }


    public Authentication getAuthentication(String token) {
        Claims claims = makeClaims(token);
        Collection<? extends GrantedAuthority> authorities = makeAuthorities(claims);
        User principal = newPrincipal(claims, authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private User newPrincipal(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        return new User(claims.getSubject(), "", authorities);
    }

    private Collection<? extends GrantedAuthority> makeAuthorities(Claims claims) {
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(CLAIMS_REGEX))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Claims makeClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // TODO :: Exception 처리 진행 필요
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}