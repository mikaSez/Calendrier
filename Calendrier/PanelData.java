package Calendrier;

import java.util.GregorianCalendar;

public interface PanelData {
    public void processData(GregorianCalendar gregorianCalendar);

    public void setPreviousAction();

    public void setNextAction();

    public void setMonthTo(int number);

    public void setYearTo(int number);

    public void processData();

}
