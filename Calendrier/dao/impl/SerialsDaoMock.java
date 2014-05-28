package Calendrier.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Calendrier.dao.SerialsDao;
import Calendrier.dto.Serial;

public class SerialsDaoMock implements SerialsDao {

    static List<Serial> series;

    static {
	series = new ArrayList<Serial>();
    }

    public SerialsDaoMock() throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	series.add(new Serial("D.B. Weiss", "Game of Thrones", "Fantasy,Action", sdf.parse("2013/12/20"), 4, 2));
	series.add(new Serial("Vince Gilligan", "Breaking Bad", "Drame, Thriller, Comedie noire", sdf.parse("2013/09/20"), 12, 5));
	series.add(new Serial("Vince Gilligan", "Breaking Bad", "Drame, Thriller, Comedie noire", sdf.parse("2013/09/20"), 13, 5));
	series.add(new Serial("Tim Kring", "Heroes", "Fantasy,Action", sdf.parse("2010/10/20"), 12, 1));
    }

    @Override
    public List<Serial> getSerialListForDay(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR)
		    && calendarDate.get(Calendar.DAY_OF_YEAR) == serieDate.get(Calendar.DAY_OF_YEAR)) {
		data.add(serie);
	    }
	}
	return data;
    }

    @Override
    public List<Serial> getSerialListForMonth(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR) && calendarDate.get(Calendar.MONTH) == serieDate.get(Calendar.MONTH)) {
		data.add(serie);
	    }
	}
	return data;
    }

    @Override
    public List<Serial> getSerialListForYear(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR)) {
		data.add(serie);
	    }
	}
	return data;
    }
}
