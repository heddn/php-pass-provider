package org.drupal.credential.hash;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.credential.dto.PasswordCredentialData;
import org.keycloak.models.credential.dto.PasswordSecretData;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test PHP password hashing.
 */
public class PhpPassPasswordHashProviderTest
{
    private PhpPassPasswordHashProvider phpPassHashProvider;

    @BeforeEach
    public void init() {
        phpPassHashProvider = new PhpPassPasswordHashProvider(PhpPassPasswordHashProviderFactory.ID);
    }

    @Test
    public void canVerify()
    {
        String rawPassword = "P@ssw0rd1";
        String encryptedPassword = "$S$DgSaXvscPCZqQcSVasxhPe.Or9r87SoZ60Q4vqCIqmwKa5h/vUFs";
        byte[] salt = StandardCharsets.UTF_8.encode("abc123").array();
        PasswordCredentialData credentialData = new PasswordCredentialData(PhpPassPasswordHashProvider.HASH_ITERATIONS, PhpPassPasswordHashProviderFactory.ID);
        PasswordSecretData secretData = new PasswordSecretData(encryptedPassword, salt);
        PasswordCredentialModel credentialModel = PasswordCredentialModel.createFromValues(credentialData, secretData);
        assertTrue(phpPassHashProvider.verify(rawPassword, credentialModel));
    }

    @Test
    public void canEncode()
    {
        String rawPassword = "P@ssw0rd1";
        PasswordCredentialModel passwordCredentialModel = phpPassHashProvider.encodedCredential(rawPassword, PhpPassPasswordHashProvider.HASH_ITERATIONS);
        String encryptedPassword = passwordCredentialModel.getPasswordSecretData().getValue();
        byte[] salt = StandardCharsets.UTF_8.encode("abc123").array();
        PasswordCredentialData credentialData = new PasswordCredentialData(PhpPassPasswordHashProvider.HASH_ITERATIONS, PhpPassPasswordHashProviderFactory.ID);
        PasswordSecretData secretData = new PasswordSecretData(encryptedPassword, salt);
        PasswordCredentialModel credentialModel = PasswordCredentialModel.createFromValues(credentialData, secretData);
        assertTrue(phpPassHashProvider.verify(rawPassword, credentialModel));
    }

    @Test
    public void canEncodeCredential()
    {
        String rawPassword = "P@ssw0rd1";
        PasswordCredentialModel credentialModel = phpPassHashProvider.encodedCredential(rawPassword, PhpPassPasswordHashProvider.HASH_ITERATIONS);
        assertTrue(phpPassHashProvider.verify(rawPassword, credentialModel));
    }
}
