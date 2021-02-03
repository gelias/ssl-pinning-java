import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SSLPinningValidatorTest {

    @Test
    public void shouldValidateAllInvalidPins(){
        var validator = new SSLPinningValidator();
        Set<String>  validPins = new HashSet<String>(){{add("xpt9");add("xyz");}};
        String domainUrl = "https://stackoverflow.com";
        assertFalse(validator.isTrustedDomain(validPins, domainUrl));
    }

    @Test
    public void shouldValidateTheFirstPublicKeyCertificateAsAValidPin(){
        var validator = new SSLPinningValidator();
        Set<String>  validPins = new HashSet<String>(){{add("xpt9");add("YGmKTnQYeZ3s7Qag/JX3dneVBmXtlQTuK1Ak1JWGVnQ=");}};
        String domainUrl = "https://stackoverflow.com";
        assertTrue(validator.isTrustedDomain(validPins, domainUrl));
    }

    @Test
    public void shouldValidateTheSecondPublicKeyCertificateAsAValidPin(){
        var validator = new SSLPinningValidator();
        Set<String>  validPins = new HashSet<String>(){{add("jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=");add("xyz");}};
        String domainUrl = "https://stackoverflow.com";
        assertTrue(validator.isTrustedDomain(validPins, domainUrl));
    }

    @Test
    public void shouldValidateAnyoneOfThePublicKeyCertificateAsAValidPins(){
        var validator = new SSLPinningValidator();
        Set<String>  validPins = new HashSet<String>(){{add("jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=");add("YGmKTnQYeZ3s7Qag/JX3dneVBmXtlQTuK1Ak1JWGVnQ=");}};
        String domainUrl = "https://stackoverflow.com";
        assertTrue(validator.isTrustedDomain(validPins, domainUrl));
    }

    @Test
    public void shoulHandleExceptionWhemInvalidDomain(){
        var validator = new SSLPinningValidator();
        String invalidDomainUrl = "https://stackoverflow.c";
        assertFalse(validator.isTrustedDomain(new HashSet<String>(), invalidDomainUrl));
    }
}