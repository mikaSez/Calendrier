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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Calendrier.Calendrier.CalenderNavigation;

public class PanelMois extends JPanel implements PanelData {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final List<JButton> buttons;
    private final CalenderNavigation navigation;
    private final GregorianCalendar displayedCalendar;
    Calendrier parent;
    private static final Color DEFAULT_COLOR = new Color(238, 238, 238);

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelMois(CalenderNavigation cn, Calendrier calendrier) {
	super();
	parent = calendrier;
	this.navigation = cn;
	displayedCalendar = new GregorianCalendar();
	this.setSize(WINDOWS_WIDTH - 20, MAIN_HEIGHT);

	buttons = new ArrayList<JButton>();

	// just some layout stuff nothing important mostly lazy positionning
	GridLayout main = new GridLayout(7, 8);
	this.setLayout(main);
	this.setLocation(5, NAVIGATION_HEIGHT + 10);
	main.setHgap(10);
	main.setVgap(5);
	int i = 6 * 7;
	initTopLabels();

	while (i-- != 0) {
	    JButton but = new JButton();
	    this.add(but);
	    buttons.add(but);
	}

	initLeftLabels();

	setPreviousAction();
	setNextAction();

    }

    /**
     * @brief used to set the action for the next button
     * */
    @Override
    public void setNextAction() {
	navigation.setNext(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		displayedCalendar.add(GregorianCalendar.MONTH, 1);
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
		displayedCalendar.add(GregorianCalendar.MONTH, -1);

		processData(displayedCalendar);
	    }
	});

    }

    /**
     * @see {@link PanelMain}
     * */
    @Override
    public void processData(GregorianCalendar gregorianCalendar) {
	gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

	int lastDay = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	String currentMonth = new SimpleDateFormat("MMMMM YYYY").format(gregorianCalendar.getTime());
	int firstDay = gregorianCalendar.get(Calendar.DAY_OF_WEEK) - 1;
	// on rempli le mois précedant
	GregorianCalendar lastMonth = (GregorianCalendar) gregorianCalendar.clone();
	lastMonth.add(GregorianCalendar.MONTH, -1);
	fillFirstWeek(lastMonth, gregorianCalendar.get(Calendar.WEEK_OF_MONTH), gregorianCalendar.get(Calendar.DAY_OF_WEEK));
	fillCourantMonth(firstDay, lastDay, gregorianCalendar.get(Calendar.WEEK_OF_MONTH));
	fillLastWeek(firstDay, lastDay, gregorianCalendar.get(Calendar.WEEK_OF_MONTH));

	navigation.setTitleField(currentMonth);

    }

    private void fillLastWeek(int firstDay, int lastDay, int debutSemaine) {
	int debut = (debutSemaine * 7 + firstDay);
	debut = debut + lastDay;
	int jours = 0;
	for (; debut < buttons.size(); debut++) {
	    buttons.get(debut).setText(++jours + "");
	    buttons.get(debut).setBackground(Color.LIGHT_GRAY);
	}
    }

    /**
     * @brief on rempli avec les valeurs du mois courant from (debutSemaine*7 +
     *        firstDay) to (lastDay)
     * @param firstDay
     *            : le jour de la semaine où on commence
     * @param lastDay
     *            : le jour de la semaine où on finit
     * @param debutSemaine
     *            : la première semaine
     * */
    private void fillCourantMonth(int firstDay, int lastDay, int debutSemaine) {
	int debut = (debutSemaine * 7 + firstDay);
	int fin = debut + lastDay;
	int jours = 0;
	for (; debut < fin; debut++) {
	    buttons.get(debut).setText(++jours + "");
	    buttons.get(debut).setBackground(DEFAULT_COLOR);
	}

    }

    /**
     * @brief on rempli avec les valeurs du mois passé, jusqu'au jour
     *        debutSemaine*7 + (jourSemaine-1);
     * @param lastMonth
     *            le mois d'avant
     * @param j
     *            le début de la semaine
     * @param k
     * */
    private void fillFirstWeek(GregorianCalendar lastMonth, int debutSemaine, int jourSemaine) {
	int jours = lastMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	int i = debutSemaine * 7 + (jourSemaine - 1);
	while (i-- > 0) {
	    buttons.get(i).setText(jours-- + "");
	    buttons.get(i).setBackground(Color.LIGHT_GRAY);
	}

    }

    /**
     * @brief s'occupe de positionner les labels à gauche
     * */
    private void initLeftLabels() {
	JLabel label = new JLabel("1");
	this.add(label, 8);
	label = new JLabel("2");
	this.add(label, (8 * 2));
	label = new JLabel("3");
	this.add(label, (8 * 3));
	label = new JLabel("4");
	this.add(label, (8 * 4));
	label = new JLabel("5");
	this.add(label, (8 * 5));
	label = new JLabel("6");
	this.add(label, (8 * 6));

    }

    /**
     * @brief methode qui s'occupe de positionner les label en haut des mois
     */
    private void initTopLabels() {
	JLabel label = new JLabel("Semaine");
	this.add(label, 0);
	label = new JLabel("Dimanche");
	this.add(label, 1);
	label = new JLabel("Lundi");
	this.add(label, 2);
	label = new JLabel("Mardi");
	this.add(label, 3);
	label = new JLabel("Mercredi");
	this.add(label, 4);
	label = new JLabel("Jeudi");
	this.add(label, 5);
	label = new JLabel("Vendredi");
	this.add(label, 6);
	label = new JLabel("Samedi");
	this.add(label, 7);
    }

    @Override
    public void setMonthTo(int number) {
	this.displayedCalendar.set(GregorianCalendar.MONTH, number);
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
