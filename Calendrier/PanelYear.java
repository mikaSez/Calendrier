package Calendrier;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;

import Calendrier.Calendrier.CalenderNavigation;
import Calendrier.dto.Serial;

public class PanelYear extends PanelDefault implements PanelData {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static String[] mois = { "Décembre", "Novembre", "Octobre", "Septembre", "Août", "Juillet", "Juin", "Mai", "Avril", "Mars",
	    "Fevrier", "Janvier" };
    private final MonthListener ml = new MonthListener();

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelYear(CalenderNavigation cn, Calendrier calendrier) {
	super(cn, calendrier, 4, 3);
	int i = 4 * 3;
	while (i-- != 0) {
	    JButton but = new JButton(mois[i]);
	    but.setBackground(DEFAULT_COLOR);
	    but.addActionListener(ml);
	    this.add(but);
	    buttons.add(but);

	}

	setPreviousAction();
	setNextAction();
	processData(displayedCalendar);

    }

    @Override
    public void processData(GregorianCalendar gregorianCalendar) {
	String currentYear = new SimpleDateFormat("YYYY").format(gregorianCalendar.getTime());
	List<Serial> series = service.getSerialListForYear(gregorianCalendar.getTime());
	for (JButton jb : buttons) {
	    jb.setBackground(DEFAULT_COLOR);
	}

	if (!series.isEmpty()) {
	    Calendar calendarDate = Calendar.getInstance();
	    for (Serial serie : series) {
		calendarDate.setTime(serie.getDate());
		buttons.get(calendarDate.get(Calendar.MONTH)).setBackground(Color.GREEN);
	    }
	}
	navigation.setTitleField(currentYear);

    }

    /**
     * @brief used to set the action for the next button
     * */
    @Override
    public void setNextAction() {
	navigation.setNext(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		displayedCalendar.add(GregorianCalendar.YEAR, 1);
		processData(displayedCalendar);
	    }
	});
    }

    /**
     * @brief used to set the action for the previous button
     * */
    @Override
    public void setPreviousAction() {
	navigation.setPrevious(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		displayedCalendar.add(GregorianCalendar.YEAR, -1);

		processData(displayedCalendar);
	    }
	});

    }

    /**
     * @brief listener pour les boutons des mois
     * */
    private class MonthListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JButton jb = (JButton) e.getSource();

	    parent.remove(1);
	    parent.add(PanelBuilder.getBuilder().setToMonthly().setMonth(buttons.indexOf(jb)).forYear(displayedCalendar.get(GregorianCalendar.YEAR))
		    .getForShow());
	    parent.repaint();

	}
    }

    @Override
    public void setMonthTo(int number) {
	return;
    }

    @Override
    public void setYearTo(int number) {
	this.displayedCalendar.set(GregorianCalendar.YEAR, number);
    }

    @Override
    public void processData() {
	processData(displayedCalendar);

    }
}
