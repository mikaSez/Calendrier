package Calendrier;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import Calendrier.Calendrier.CalenderNavigation;
import Calendrier.dto.Serial;

public class PanelDay extends PanelDefault implements PanelData {

    /**
     * 
     */
    private static final long serialVersionUID = 1189697036182717560L;

    public PanelDay(CalenderNavigation cn, Calendrier calendrier) {
	super(cn, calendrier);

	setPreviousAction();
	setNextAction();
	setTitleAction();

    }

    @Override
    public void processData(GregorianCalendar gregorianCalendar) {
	List<Serial> series = service.getSerialListForDay(displayedCalendar.getTime());
	this.removeAll();
	if (series.isEmpty()) {
	    JLabel lab = new JLabel("Pas de series prévues aujourd'hui :(");
	    lab.setBackground(Color.LIGHT_GRAY);
	    Font fonte = new Font("TimesRoman ", Font.ITALIC, 30);
	    lab.setFont(fonte);
	    this.add(lab);
	    this.repaint();
	} else {
	    super.setLayoutRows(series.size());
	    // nombre maximal optimal 4 series / jour: au délà avec le design
	    // actuel les texte est caché
	    for (Serial serie : series) {
		Font fonte = new Font("TimesRoman ", Font.PLAIN, 10);
		JButton but = new JButton();

		but.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		but.setFont(fonte);
		but.setText(serie.toDisplay());

		this.add(but);
	    }
	}
	String theDay = new SimpleDateFormat("dd MMMMM YYYY").format(gregorianCalendar.getTime());
	navigation.setTitleField(theDay);
    }

    /**
     * @brief used to set the action for the next button
     * */
    @Override
    public void setNextAction() {
	navigation.setNext(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		displayedCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
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
		displayedCalendar.add(GregorianCalendar.DAY_OF_MONTH, -1);

		processData(displayedCalendar);
	    }
	});

    }

    /**
     * @brief gère l'action produite par un click sur le titre dans le cas du
     *        jour affiche le mois
     * */
    private void setTitleAction() {
	navigation.setTitleAction(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
		parent.remove(1);
		parent.add(PanelBuilder.getBuilder().setToMonthly().forYear(displayedCalendar.get(GregorianCalendar.YEAR))
		        .forMonth(displayedCalendar.get(GregorianCalendar.MONTH)).getForShow());
		parent.repaint();
	    }
	});
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
	this.displayedCalendar.set(GregorianCalendar.DAY_OF_MONTH, number);

    }

    @Override
    public void processData() {
	processData(displayedCalendar);

    }

}
