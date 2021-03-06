package Calendrier;

import Calendrier.Calendrier.CalenderNavigation;

import javax.swing.*;

public class PanelBuilder {
    private static PanelBuilder builder;
    private final CalenderNavigation navigation;
    private PanelData panel;
    private Calendrier calendrier;

    private PanelBuilder(CalenderNavigation cn) {
	navigation = cn;
    }

    public static PanelBuilder getBuilder() {
	assert (builder != null);
	return builder;
    }

    /**
     * @brief singleton fun.
     * 
     * */
    public synchronized static PanelBuilder getBuilder(CalenderNavigation cn, Calendrier calendrier) {
	if (builder == null) {
	    builder = new PanelBuilder(cn);
	    builder.calendrier = calendrier;
	}
	return builder;
    }

    /**
     * @brief reset la date
     * */
    public PanelBuilder setToMonthly() {
	panel = new PanelMois(navigation, calendrier);
	return this;
    }

    /**
     * @brief reset la date
     * */
    public PanelBuilder setToYearly() {
	panel = new PanelYear(navigation, calendrier);
	return this;
    }

    /**
     * @brief reset la date
     * */
    public PanelBuilder setToDaily() {
	panel = new PanelDay(navigation, calendrier);
	return this;
    }

    public JComponent getForShow() {
	panel.processData();
	return (JComponent) panel;
    }

    public PanelBuilder forYear(int number) {
	panel.setYearTo(number);
	return this;

    }

    public PanelBuilder forDay(int number) {
	panel.setDayTo(number);
	return this;

    }

    public PanelBuilder forMonth(int number) {
	panel.setMonthTo(number);
	return this;
    }

}
