package by.tr.op.service.impl;

import by.tr.op.bean.User;
import by.tr.op.service.exception.LoginRegexpException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthorizationTest {
    
    public AuthorizationTest() {
    }

    @Test(expected = LoginRegexpException.class)
    public void testSignIn() throws Exception {
        char[] login = "---".toCharArray();
        char[] password = "123".toCharArray();
        Authorization instance = new Authorization();
        User expResult = new User();
        User result = instance.signIn(login, password);
        assertEquals(expResult, result);
    }
    
}
