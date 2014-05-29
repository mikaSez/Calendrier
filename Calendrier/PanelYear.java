package Calendrier;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;

import Calendrier.Calendrier.CalenderNavigation;
import Calendrier.dto.Serial;

public class PanelYear extends PanelDefault {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static String[] mois = { "Décembre", "Novembre", "Octobre", "Septembre", "Août", "Juillet", "Juin", "Mai", "Avril", "Mars",
	    "Fevrier", "Janvier" };
    private final MonthListener monthListener = new MonthListener();

    private final static int NB_ROW = 4;
    private final static int NB_COLUMN = 3;

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelYear(CalenderNavigation cn, Calendrier calendrier) {
	super(cn, calendrier, NB_ROW, NB_COLUMN);
	int i = NB_ROW * NB_COLUMN;
	while (i-- != 0) {
	    JButton but = new JButton(mois[i]);
	    but.setBackground(DEFAULT_COLOR);
	    but.addActionListener(monthListener);
	    but.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
	clear();
	if (!series.isEmpty()) {
	    Calendar calendarDate = Calendar.getInstance();
	    for (Serial serie : series) {

		calendarDate.setTime(serie.getDate());
		JButton jButton = buttons.get(calendarDate.get(Calendar.MONTH));
		jButton.setBackground(Color.GREEN);
		String toolTip = constructToolTip(serie, jButton.getToolTipText());

		jButton.setToolTipText(toolTip);
		;
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
	    parent.add(PanelBuilder.getBuilder().setToMonthly().forMonth(buttons.indexOf(jb)).forYear(displayedCalendar.get(GregorianCalendar.YEAR))
		    .getForShow());
	    parent.repaint();

	}
    }

}
