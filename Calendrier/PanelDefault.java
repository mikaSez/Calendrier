package Calendrier;

import static Calendrier.Calendrier.MAIN_HEIGHT;
import static Calendrier.Calendrier.NAVIGATION_HEIGHT;
import static Calendrier.Calendrier.WINDOWS_WIDTH;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Calendrier.Calendrier.CalenderNavigation;
import Calendrier.service.CalendrierService;

public abstract class PanelDefault extends JPanel {
    /**
     * 
     */
    protected static final long serialVersionUID = 1L;
    protected final List<JButton> buttons;
    protected final CalenderNavigation navigation;
    protected final Calendrier parent;
    protected final GregorianCalendar displayedCalendar;
    protected static final Color DEFAULT_COLOR = new Color(238, 238, 238);
    private final GridLayout main;
    protected static CalendrierService service;

    static {
	service = new CalendrierService();
    }

    /**
     * @param cn
     * @param calendrier
     * @brief répond du look du calendrier
     * */
    public PanelDefault(CalenderNavigation cn, Calendrier calendrier, int rows, int cols) {
	super();
	this.navigation = cn;
	parent = calendrier;
	displayedCalendar = new GregorianCalendar();
	this.setSize(WINDOWS_WIDTH - 20, MAIN_HEIGHT);

	buttons = new ArrayList<JButton>();

	// just some layout stuff nothing important mostly lazy positionning
	main = new GridLayout(rows, cols);
	this.setLayout(main);
	this.setLocation(5, NAVIGATION_HEIGHT + 10);
	main.setHgap(10);
	main.setVgap(5);
	setTitleAction();

    }

    protected void setLayoutCols(int cols) {
	main.setColumns(cols);
    }

    protected void setLayoutRows(int rows) {
	main.setRows(rows);
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
}
