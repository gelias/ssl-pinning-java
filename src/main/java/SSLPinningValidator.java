import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Set;

public class SSLPinningValidator {
    public boolean isTrustedDomain(Set<String> validPins, String domainUrl) {

        try {
            URL url = new URL(domainUrl);
            HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) url.openConnection();
            httpsUrlConnection.connect();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Certificate[] certificates = httpsUrlConnection.getServerCertificates();

            for (Certificate certificate : certificates) {

                byte[] publicKey = certificate.getPublicKey().getEncoded();
                md.update(publicKey, 0, publicKey.length);
                String pin = Base64.getEncoder().encodeToString(md.digest());

                if(validPins.contains(pin))
                    return true;
            }
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
