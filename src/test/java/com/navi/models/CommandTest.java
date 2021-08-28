package com.navi.models;

import com.navi.models.Loan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandTest {


    @Before
    public void before() {

    }

    @Test
    public void testWith3Parameters() {
        Command c = new Command("COMMAND PARAM1 PARAM2 PARAM3");
        assertEquals("COMMAND", c.getCommandName());
        assertEquals(3, c.getParams().size());
    }

    @Test
    public void testWith4Parameters() {
        Command c = new Command("COMMAND PARAM1 PARAM2 PARAM3 PARAM4");
        assertEquals("COMMAND", c.getCommandName());
        assertEquals(4, c.getParams().size());
    }

}
