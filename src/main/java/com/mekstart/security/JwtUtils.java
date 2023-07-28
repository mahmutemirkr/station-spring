package com.mekstart.security;

import com.mekstart.exception.message.ErrorMessage;
import com.mekstart.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class JwtUtils {


    /*Filter*/
    //1 : JWT generate
    //2 : JWT Valide
    //3 : JWT --> userName

    private String jwtSecret = "sboot"; //Secret Key oluşturuldu

    private long jwtExpirationsMs = 86400000; // 1 Gün -> 24*60*60*1000

    //!!! ****************** GENERATE TOKEN ******************

    //JJWT bağımlılığı bu işlemi bize yapacak
    public String generateToken(Authentication authentication){  //JWT generate edildi

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // login olan kullanıcılar geldi
        //user bilgilerini security anlayacağı şekilde getirdi. -> Bu bilgilere userDetails ile ulaşacağım

        //Username, jwt token üretiyor
        return Jwts.builder().
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + jwtExpirationsMs)).
                signWith(SignatureAlgorithm.HS512,jwtSecret).
                compact();

    }

    /// !!! *************** VALIDATE TOKEN *******************

    public boolean validateToken(String token){ //JWT token gelecek validate işlemi yapacak

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); //parametreden gelen token parseClaimsJws ile token parse edilerek validate yapıyor.
            return true;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false ;

        // e.printStackTrace(); kullanmamın sebebi hangi exception fırlıyor onu görmek için.


    }

    /*Interview*/
    //Soru:Hangi bilgi üzerinden JWT token generate ettin?
    //Cevap: JWT token userName ile generade ettik ( username üzerinden oluşturduk)
    //Soru2: JWT çekerken ne kullandın
    //Cevap2: JJWT bağımlılıgını kullandım. JJWT üzerinden username çekildi. Dönen Jwts JJWT bağımlılıgından geliyor.

    //***************** JWT tokenden userName'i alalım ***************

    public String getUserNameFromJwtToken(String token){

        return Jwts.parser().
                setSigningKey(jwtSecret).
                parseClaimsJws(token).
                getBody().
                getSubject();
    }



}