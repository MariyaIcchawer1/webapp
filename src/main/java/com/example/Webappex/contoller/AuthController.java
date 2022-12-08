package com.example.Webappex.contoller;

import com.example.Webappex.payload.JwtAuthRequest;
import com.example.Webappex.payload.JwtAuthresponse;
import com.example.Webappex.security.CustomerUserDetailsService;
import com.example.Webappex.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

   /* @PostMapping("/login")
    public ResponseEntity<JwtAuthresponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
        String token=this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthresponse response= new JwtAuthresponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthresponse>(response, HttpStatus.OK);
    }
    */



   /* private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
           try{
               this.authenticationManager.authenticate(authenticationToken);
           }
           catch(BadCredentialsException e){
               System.out.println("Invalid details");
               throw new Exception("Invalid username or passwrd");

        }




*/




    @PostMapping("/authenticate")
    public JwtAuthresponse authenticate(@RequestBody JwtAuthRequest userRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw  new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = customerUserDetailsService.loadUserByUsername(userRequest.getUsername());

        final String token =
                jwtTokenHelper.generateToken(userDetails);

        return  new JwtAuthresponse(token);
    }
    }

