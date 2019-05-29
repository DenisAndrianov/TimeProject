import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class Test {




    private static Algorithm algorithmHS = Algorithm.HMAC256("secret");
    private static JWTVerifier verifier = JWT.require(algorithmHS).build();

    public static void main (String[] args) {
        String token = JWT.create().withSubject("userid999").sign(algorithmHS);
        System.out.println(token);
        String answer = verifier.verify(token).getSubject();
        System.out.println(answer);

    }
}
