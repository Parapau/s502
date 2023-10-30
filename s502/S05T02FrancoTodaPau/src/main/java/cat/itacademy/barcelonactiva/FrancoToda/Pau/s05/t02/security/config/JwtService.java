package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;	
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String secret_key = "1bce8d1c10f17555d0e3f2196061bb8396c952bb6ca8146340497e2dd114485c";
	
	

	public String extractNom(String token) {
		return extractAllClaims(token).getSubject();
	}

	
	public <T> T extractClaims (String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims (token);
		return claimsResolver.apply(claims);
		
	}
	
	
	private Claims extractAllClaims (String token) {
		return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
	}

	
	
	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		
		
		
		return Jwts.builder().claims(extractClaims).subject(userDetails.getUsername()).
				issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getSecretKey(), SIG.HS256).compact();
	}

	
	
	public String generateToken(UserDetails userDetails) {
		
		return generateToken(new HashMap<>(), userDetails);
	}
	
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String nom = extractNom(token);
		return (nom.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	public boolean isTokenExpired(String token) {
		return extractClaims(token, Claims::getExpiration).before(new Date());
	}

	private SecretKey getSecretKey() {
		byte[] keyBites = Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(keyBites);
	}
	
	
	
	
}
