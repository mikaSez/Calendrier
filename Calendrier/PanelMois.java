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
import javax.swing.JLabel;

import Calendrier.Calendrier.CalenderNavigation;
import Calendrier.dto.Serial;

public class PanelMois extends PanelDefault implements PanelData {

    /**
     * 
     */
    private static final long serialVersionUID = 6355653823004753713L;
    private final DayListener dayListener = new DayListener();
    private final static int NB_ROW = 7;
    private final static int NB_COLUMN = 8;

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelMois(CalenderNavigation cn, Calendrier calendrier) {
	super(cn, calendrier, NB_ROW, NB_COLUMN);

	int i = (NB_ROW - 1) * (NB_COLUMN - 1);
	initTopLabels();

	while (i-- != 0) {
	    JButton but = new JButton();
	    this.add(but);
	    buttons.add(but);
	}

	initLeftLabels();

	setPreviousAction();
	setNextAction();
	setTitleAction();
    }

    /**
     * @brief gère l'action produite par un click sur le titre dans le cas du
     *        mois affiche l'année
     * */
    private void setTitleAction() {
	navigation.setTitleAction(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		parent.remove(1);
		parent.add(PanelBuilder.getBuilder().setToYearly().forYear(displayedCalendar.get(GregorianCalendar.YEAR)).getForShow());
		parent.repaint();
	    }
	});
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
	clear();
	clearButton();
	List<Serial> series = service.getSerialListForMonth(gregorianCalendar.getTime());
	int lastDay = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	String currentMonth = new SimpleDateFormat("MMMMM YYYY").format(gregorianCalendar.getTime());
	int firstDay = gregorianCalendar.get(Calendar.DAY_OF_WEEK) - 1;
	// on rempli le mois précedent
	GregorianCalendar lastMonth = (GregorianCalendar) gregorianCalendar.clone();
	lastMonth.add(GregorianCalendar.MONTH, -1);
	fillFirstWeek(lastMonth, gregorianCalendar.get(Calendar.WEEK_OF_MONTH), gregorianCalendar.get(Calendar.DAY_OF_WEEK));
	fillCourantMonth(firstDay, lastDay, gregorianCalendar.get(Calendar.WEEK_OF_MONTH), series);
	fillLastWeek(firstDay, lastDay, gregorianCalendar.get(Calendar.WEEK_OF_MONTH));

	navigation.setTitleField(currentMonth);

    }

    private void clearButton() {

	for (JButton jb : buttons) {

	    jb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    for (ActionListener al : jb.getActionListeners()) {
		jb.removeActionListener(al);
	    }
	}

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
     * @param series
     *            : les series qui passent ce mois
     * */
    private void fillCourantMonth(int firstDay, int lastDay, int debutSemaine, List<Serial> series) {
	int debut = (debutSemaine * 7 + firstDay);
	int fin = debut + lastDay;
	int jours = 0;
	boolean isDefault;

	Calendar calendarDate = Calendar.getInstance();

	for (; debut < fin; debut++) {
	    isDefault = true;
	    JButton jButton = buttons.get(debut);

	    jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    jButton.setText(++jours + "");
	    jButton.addActionListener(dayListener);
	    for (Serial serie : series) {
		calendarDate.setTime(serie.getDate());

		if (calendarDate.get(Calendar.DAY_OF_MONTH) == jours) {
		    jButton.setBackground(Color.GREEN);
		    String toolTip = constructToolTip(serie, jButton.getToolTipText());

		    jButton.setToolTipText(toolTip);
		    isDefault = false;
		}
	    }
	    if (isDefault) {
		jButton.setBackground(DEFAULT_COLOR);
	    }

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
    public void setDayTo(int number) {
	return;

    }

    @Override
    public void processData() {
	processData(displayedCalendar);

    }

    /**
     * @brief listener pour les boutons des jours
     * */
    private class DayListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JButton jb = (JButton) e.getSource();
	    PanelBuilder pb = PanelBuilder.getBuilder();
	    pb.setToDaily();
	    pb.forYear(displayedCalendar.get(GregorianCalendar.YEAR));
	    pb.forMonth(displayedCalendar.get(GregorianCalendar.MONTH));
	    pb.forDay(Integer.parseInt(jb.getText()));
	    parent.remove(1);
	    parent.add(pb.getForShow());
	    parent.repaint();

	}
    }

}
