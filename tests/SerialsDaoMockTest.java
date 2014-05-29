package tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Calendrier.service.CalendrierService;

public class SerialsDaoMockTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws ParseException {
	CalendrierService service = new CalendrierService();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	assertEquals(4, service.getSerialListForYear(sdf.parse("2013/12/20")).size());
	assertEquals(1, service.getSerialListForYear(sdf.parse("2010/12/20")).size());
	assertEquals(1, service.getSerialListForMonth(sdf.parse("2010/10/20")).size());
	assertEquals(0, service.getSerialListForMonth(sdf.parse("2013/09/20")).size());
	assertEquals(1, service.getSerialListForDay(sdf.parse("2010/10/20")).size());

    }

}
