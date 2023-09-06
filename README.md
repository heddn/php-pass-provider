# [Keycloak](https://www.keycloak.org) PHP [Password Provider](https://www.keycloak.org/docs-api/15.0/javadocs/org/keycloak/credential/hash/PasswordHashProvider.html)

See [releases](https://github.com/heddn/php-pass-provider/releases) to download a JAR file. Almost certaintly you want the `php-password-hash-VERSION-jar-with-dependencies.jar`.  This JAR file should be uploaded following the
[normal procedures](https://www.keycloak.org/docs/latest/server_installation/index.html#_operator-extensions)
for a custom extension.

If you need to rebuild the JAR file for use with a more modern version of Java, simply `mvn clean install` will generate the required artifacts. 

Tested with Keycloak 15-22, but generally speaking it should work on most (all) versions of Keycloak.
