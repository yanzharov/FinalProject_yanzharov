package by.tr.op.controller;

import by.tr.op.command.Command;
import by.tr.op.command.impl.GetUsers;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandProviderTest {
    
    public CommandProviderTest() {
    }

    @Test
    public void testGetCommand() {
        String commandName = "GET_USERS";
        CommandProvider instance = CommandProvider.getInstance();
        Command expResult = new GetUsers();
        Command result = instance.getCommand(commandName);
        assertEquals(expResult.getClass(), result.getClass());
    }
    
}
