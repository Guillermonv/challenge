package com.santander.challenge.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.santander.challenge.error.ErrorEnum;
import com.santander.challenge.model.User;
import com.santander.challenge.security.model.Credentials;
import com.santander.challenge.service.UserService;
import com.santander.challenge.utils.RolesEnum;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("auth")
    @ApiOperation(value = "endpoint de autenticaciòn")
    public ResponseEntity<Credentials> auth(@RequestBody Credentials credentials) {
        Credentials account = new Credentials();
        try {
            LOGGER.info("Login with username" + credentials.getUsername() + "-" + credentials.getPassword());
            User user = userService.getUser(credentials.getUsername(), credentials.getPassword());
            String token = getJWTToken(user.getRole());
            account.setUsername(credentials.getUsername());
            account.setToken(token);
            account.setRole(credentials.getRole());
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (UserException e) {
            LOGGER.error(ErrorEnum.INVALID_USER_OR_PASSWORD.getDescription());
            account.setUsername(ErrorEnum.INVALID_USER_OR_PASSWORD.getDescription());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(account);
        } catch (Exception e) {
            account.setUsername(ErrorEnum.INVALID_USER_OR_PASSWORD.getDescription());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(account);
        }
    }

    private String getJWTToken(String role) {
        List<GrantedAuthority> grantedAuthorities;

        if (role.equalsIgnoreCase(RolesEnum.ADMIN.getRole())) {
            grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(RolesEnum.ADMIN.getRoleType());
        } else {
            grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(RolesEnum.USER.getRoleType());
        }

        String token = Jwts
                .builder()
                .setId("challengeJWT")
                .setSubject(role)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        "mySecretKey".getBytes()).compact();

        return "Bearer " + token;
    }
    
}


