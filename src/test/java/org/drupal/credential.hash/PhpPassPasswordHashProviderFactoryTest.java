package org.drupal.credential.hash;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.KeycloakSession;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertEquals;

/**
 * Unit test PHP password hashing.
 */
@RunWith(EasyMockRunner.class)
public class PhpPassPasswordHashProviderFactoryTest
{
    @TestSubject
    private PhpPassPasswordHashProviderFactory factory = new PhpPassPasswordHashProviderFactory();

    @Mock
    private KeycloakSession mockSession;

    @Test
    public void create()
    {
        PasswordHashProvider provider = factory.create(mockSession);
        assertThat(provider, instanceOf(PhpPassPasswordHashProvider.class));
        assertEquals(factory.getId(), PhpPassPasswordHashProviderFactory.ID);
    }
}
