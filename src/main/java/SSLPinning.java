import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class SSLPinning {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        var sslPinningValidator = new SSLPinningValidator();
        boolean isTrustedDomain = sslPinningValidator.isTrustedDomain(new HashSet<String>() {{
            add(args[0]);
            add(args[1]);
        }}, args[2]);

        if(isTrustedDomain)
            System.out.println(String.format("Domain %s succesfully validate",args[2]));
        else
            System.out.println(String.format("Cert destination is not secure, may considered domain's cert hacked",args[2]));
    }



}

