# SSL Pinning Validation

SSL Pinning is a good practice for mitigates a man-in-the-middle attack!

## Getting pins from destination domain
To exchange a domain, first all you need to obtain the public keys from your chain domain, like this:

```bash
bash generate_pins.sh <domain.com>
```

Now you can use generated SHA-256 strings as pins to double check, validating the cert origin!

## How to run

Build project ...
```bash
docker build -t ssl-pinning .
```

Running ...
```bash
docker run -it ssl-pinning <pin#1> <pin#2> <https://domain.com>
```

# Test

```bash
docker run -it ssl-pinning YGmKTnQYeZ3s7Qag/JX3dneVBmXtlQTuK1Ak1JWGVnQ= jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0= https://stackoverflow.com
```
The output should be
```bash
Domain https://stackoverflow.com succesfully validate
```

# References
* https://owasp.org/www-community/controls/Certificate_and_Public_Key_Pinning#
* https://www.certificate-transparency.org/faq#TOC-What-is-an-SCT-
* https://github.com/OWASP/owasp-mstg/issues/892
* https://appmattus.medium.com/android-security-ssl-pinning-1db8acb6621e
* https://pt.stackoverflow.com/questions/479801/o-que-%C3%A9-ssl-pinning  *
* https://developer.android.com/training/articles/security-ssl#java
* https://mundohacker.net.br/android-ssl-pinning/
* https://mundohacker.net.br/android-ssl-pinning/
* https://owasp.org/www-project-mobile-security/