package test;

import edu.com.Service;
import org.junit.Test;
import java.sql.SQLException;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
public class Tests{
    @Test
    public void findAllBySolutionTest() throws SQLException {
        assertFalse(Service.findAllBySolution(6).stream().noneMatch(el -> el.getX() == 6));
    }
    @Test
    public void isSolutionTest() throws SQLException {
        assertTrue(Service.solution("2*x+5=17", 6));
    }
    @Test
    public void hasValidBracketsTest()  {
        assertTrue(Service.isBracketsRight("(2*x+5)=17"));
    }
    @Test
    public void hasValidSymbolsTest()  {
        assertTrue(Service.isSignsRight("(2*x*-5)=17"));
    }

}