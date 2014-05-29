package Calendrier;

import Calendrier.Calendrier.*;
import Calendrier.dto.Serial;
import Calendrier.service.CalendrierService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static Calendrier.Calendrier.*;

public abstract class PanelDefault extends JPanel implements PanelData {
    /**
     * 
     */
    protected static final long serialVersionUID = 1L;
    static final Color DEFAULT_COLOR = new Color(238, 238, 238);
    static final CalendrierService service;
    static {
	service = new CalendrierService();
    }
    private final GridLayout main;
    List<JButton> buttons;
    CalenderNavigation navigation;
    Calendrier parent;
    GregorianCalendar displayedCalendar;

    /**
     * @param cn la navigation
     * @param calendrier l'appli
     * @brief répond du look du calendrier
     * */
    PanelDefault(CalenderNavigation cn, Calendrier calendrier, int rows, int cols) {
	super();
	main = new GridLayout(rows, cols);

	// just some layout stuff nothing important mostly lazy positionning

	initialisation(cn, calendrier);

    }

    PanelDefault(CalenderNavigation cn, Calendrier calendrier) {
	super();
	main = new GridLayout(1, 1);
	initialisation(cn, calendrier);
    }

    private void initialisation(CalenderNavigation cn, Calendrier calendrier) {
	this.navigation = cn;
	this.parent = calendrier;
	this.displayedCalendar = new GregorianCalendar();
	this.setSize(WINDOWS_WIDTH - 20, MAIN_HEIGHT);
	this.buttons = new ArrayList<JButton>();

	// just some layout stuff nothing important mostly lazy positionning

	this.setLayout(main);
	this.setLocation(5, NAVIGATION_HEIGHT + 10);
	main.setHgap(10);
	main.setVgap(5);
	setTitleAction();
    }

    protected void setLayoutCols(int cols) {
	main.setColumns(cols);
    }

    void setLayoutRows(int rows) {
	main.setRows(rows);
    }

    void clear() {
	for (JButton jb : buttons) {

	    jb.setBackground(DEFAULT_COLOR);
	    jb.setToolTipText("");

	}
    }

    String constructToolTip(Serial serie, String data) {
	StringBuilder sb = new StringBuilder();
	sb.append("<html>");
	if (data != null) {
	    data = data.replaceAll("<html>", "");
	    data = data.replaceAll("</html>", "");
	    sb.append(data);

	    sb.append("<hr/>");
	}
	sb.append("<br/><strong> Title : </strong>").append(serie.getTitle());
	sb.append("<br/><strong> Saison </strong>:").append(serie.getNumSaison());
	sb.append("<br/> <strong>Série </strong>:").append(serie.getNumSerie());

	sb.append("</html>");
	return sb.toString();
    }

    /**
     * @brief par défaut le click sur le titre ne produit aucune action
     * */
    private void setTitleAction() {
	navigation.setTitleAction(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent action) {
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
