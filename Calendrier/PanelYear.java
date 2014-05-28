package Calendrier;

import static Calendrier.Calendrier.MAIN_HEIGHT;
import static Calendrier.Calendrier.NAVIGATION_HEIGHT;
import static Calendrier.Calendrier.WINDOWS_WIDTH;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Calendrier.Calendrier.CalenderNavigation;

public class PanelYear extends JPanel implements PanelData {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final List<JButton> buttons;
    private final CalenderNavigation navigation;
    private final Calendrier parent;
    private final GregorianCalendar displayedCalendar;
    private static final Color DEFAULT_COLOR = new Color(238, 238, 238);

    private final static String[] mois = { "Décembre", "Novembre", "Octobre", "Septembre", "Août", "Juillet", "Juin", "Mai", "Avril", "Mars",
	    "Fevrier", "Janvier" };
    private final MonthListener ml = new MonthListener();

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelYear(CalenderNavigation cn, Calendrier calendrier) {
	super();
	this.navigation = cn;
	parent = calendrier;
	displayedCalendar = new GregorianCalendar();
	this.setSize(WINDOWS_WIDTH - 20, MAIN_HEIGHT);

	buttons = new ArrayList<JButton>();

	// just some layout stuff nothing important mostly lazy positionning
	GridLayout main = new GridLayout(4, 3);
	this.setLayout(main);
	this.setLocation(5, NAVIGATION_HEIGHT + 10);
	main.setHgap(10);
	main.setVgap(5);
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
