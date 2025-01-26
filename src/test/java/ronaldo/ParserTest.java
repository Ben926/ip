package ronaldo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    @Test
    public void parseCommandTest() throws RonaldoException {
        assertEquals(Command.LIST, Parser.parseCommand("list"));
        assertEquals(Command.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Command.UNMARK, Parser.parseCommand("unmark 2"));
        assertEquals(Command.TODO, Parser.parseCommand("todo buy food"));
        assertEquals(Command.DEADLINE, Parser.parseCommand("deadline return book /by 2025-01-26"));
        assertEquals(Command.EVENT, Parser.parseCommand("event party /from 2025-01-25 /to 2025-01-27"));
        assertEquals(Command.DELETE, Parser.parseCommand("delete 1"));
        assertEquals(Command.BYE, Parser.parseCommand("bye"));
    }

    @Test
    public void parseToDoCommandTest() throws RonaldoException {
        Task task = Parser.parseToDoCommand("todo buy food");
        assertEquals("[T] [ ] buy food", task.toString());
    }

    @Test
    public void parseDeadlineCommandTest() throws RonaldoException {
        Task task = Parser.parseDeadlineCommand("deadline return book /by 2025-01-26");
        assertEquals("[D] [ ] return book (by: Jan 26 2025)", task.toString());
    }

    @Test
    public void parseEventCommandTest() throws RonaldoException {
        Task task = Parser.parseEventCommand("event party /from 2025-01-25 /to 2025-01-27");
        assertEquals("[E] [ ] party (from Jan 25 2025 to Jan 27 2025)", task.toString());
    }

    @Test
    public void parseEventCommand_invalidDateFormat_test() {
        assertThrows(RonaldoException.class, () ->
                Parser.parseEventCommand("event party /from 2025-01-06 /to 02-26"));
    }

    @Test
    public void parseTodoCommand_emptyDescription_test() {
        assertThrows(RonaldoException.class, () ->
                Parser.parseToDoCommand("todo"));
    }

    @Test
    public void parseDeadlineCommand_invalidDateFormat_test() {
        assertThrows(RonaldoException.class, () ->
                Parser.parseDeadlineCommand("deadline return book by tomorrow"));
    }

}
