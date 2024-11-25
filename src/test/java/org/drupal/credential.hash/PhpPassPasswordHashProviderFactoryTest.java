package org.drupal.credential.hash;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.KeycloakSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.easymock.EasyMock.replay;

/**
 * Unit test PHP password hashing factory.
 */
@ExtendWith(EasyMockExtension.class)
public class PhpPassPasswordHashProviderFactoryTest
{
    @TestSubject
    private PhpPassPasswordHashProviderFactory factory = new PhpPassPasswordHashProviderFactory();

    @Mock
    private KeycloakSession mockSession;

    @Test
    public void create()
    {
        replay(mockSession);
        PasswordHashProvider provider = factory.create(mockSession);
        assertTrue(provider instanceof PhpPassPasswordHashProvider);
        assertEquals(factory.getId(), PhpPassPasswordHashProviderFactory.ID);
    }
}
