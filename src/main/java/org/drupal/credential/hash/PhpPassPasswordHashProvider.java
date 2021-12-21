package org.drupal.credential.hash;

import com.mollom.phpass.PasswordHasher;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;

import java.nio.charset.StandardCharsets;

public class PhpPassPasswordHashProvider implements PasswordHashProvider {

    private final String providerId;
    private final PasswordHasher passwordHasher;
    // Salt is not used with decrypting password, but the interface requires a value so a dummy value is fine.
    private static final byte[] salt = StandardCharsets.UTF_8.encode("abc123").array();
    // Iterations are not used while encrypting password, upstream Mollom library has a hard-coded value of 15.
    // But the interface requires a value, so we provide one.
    public static final int HASH_ITERATIONS = 15;

    public PhpPassPasswordHashProvider(String providerId) {
        this.providerId = providerId;
        this.passwordHasher = new PasswordHasher();
    }

    @Override
    public boolean policyCheck(PasswordPolicy policy, PasswordCredentialModel credential) {
        return true;
    }

    @Override
    public PasswordCredentialModel encodedCredential(String rawPassword, int iterations) {
        String encodedPassword = encodedCredential(rawPassword);
        return PasswordCredentialModel.createFromValues(providerId, salt, HASH_ITERATIONS, encodedPassword);
    }

    @Override
    public String encode(String rawPassword, int iterations) {
        return encodedCredential(rawPassword);
    }

    @Override
    public boolean verify(String rawPassword, PasswordCredentialModel credential) {
        String encryptedPassword = credential.getPasswordSecretData().getValue();
        return passwordHasher.isMatch(rawPassword, encryptedPassword);
    }

    @Override
    public void close() {
    }

    private String encodedCredential(String rawPassword) {
        return passwordHasher.createHash(rawPassword);
    }
}
