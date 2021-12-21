package org.drupal.credential.hash;

import org.junit.Before;
import org.junit.Test;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.credential.dto.PasswordCredentialData;
import org.keycloak.models.credential.dto.PasswordSecretData;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

/**
 * Unit test PHP password hashing factory.
 */
public class PhpPassPasswordHashProviderTest
{
    private PhpPassPasswordHashProvider phpPassHashProvider;

    @Before
    public void setup() {
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
        String encryptedPassword = phpPassHashProvider.encode(rawPassword, PhpPassPasswordHashProvider.HASH_ITERATIONS);
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
