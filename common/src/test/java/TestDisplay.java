import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wallentines.atimer.TimerDisplayImpl;

public class TestDisplay {

    @Test
    public void testTimeString() {

        Assertions.assertEquals("00:01", TimerDisplayImpl.toTimeString(1));
        Assertions.assertEquals("00:53", TimerDisplayImpl.toTimeString(53));
        Assertions.assertEquals("01:24", TimerDisplayImpl.toTimeString(60 + 24));
        Assertions.assertEquals("41:33", TimerDisplayImpl.toTimeString((41 * 60) + 33));
        Assertions.assertEquals("01:01:10", TimerDisplayImpl.toTimeString((61 * 60) + 10));
        Assertions.assertEquals("1:00:00:01", TimerDisplayImpl.toTimeString((24 * 60 * 60) + 1));

    }

}
