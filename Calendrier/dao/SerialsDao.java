package Calendrier.dao;

import Calendrier.dto.Serial;

import java.util.Date;
import java.util.List;

public interface SerialsDao {
    public List<Serial> getSerialListForDay(Date date);

    public List<Serial> getSerialListForMonth(Date date);

    public List<Serial> getSerialListForYear(Date date);
}
