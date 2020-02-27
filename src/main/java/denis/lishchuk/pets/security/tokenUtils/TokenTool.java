package denis.lishchuk.pets.security.tokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import denis.lishchuk.pets.security.dto.UserDetailsDto;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenTool {

    public String createToken(String login, String role){
        String jwt;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR,3);
        Date validTo = cal.getTime();

        jwt = Jwts.builder().setIssuer("OurProject")
                .claim("login",login)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(validTo)
                .signWith(SignatureAlgorithm.HS256,"ourKey")
                .compact();
        return jwt;
    }

    public boolean isTokenValid(String token){
        boolean valid  = false;
        try{
            Jwts.parser().setSigningKey("ourKey").parseClaimsJws(token);
            valid = true;
        }catch (Exception e){
        }
        return valid;
    }

    public UserDetails getUserByToken(String token){
        Claims claims = Jwts.parser().setSigningKey("ourKey").parseClaimsJws(token).getBody();
        String role = (String) claims.get("role");
        GrantedAuthority role1 = new SimpleGrantedAuthority(role);
        String login = (String) claims.get("login");
        return new UserDetailsDto(login, Collections.singletonList(role1));
    }
}
