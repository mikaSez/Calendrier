package Calendrier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Calendrier extends JPanel {

    public static final int WINDOWS_WIDTH = 700; // taille appli - le menu
    public static final int WINDOWS_HEIGHT = 530;
    public static final int NAVIGATION_HEIGHT = 50;
    public static final int MAIN_HEIGHT = 430;

    private static final long serialVersionUID = 4325406721569230614L;

    private Calendrier() {

	this.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	this.setLayout(null);
	CalenderNavigation cn = new CalenderNavigation();
	this.add(cn);
    PanelBuilder pb = PanelBuilder.getBuilder(cn, this);
	this.add(pb.setToYearly().getForShow());

    }

    public static void main(String[] args) {

	JFrame application = new JFrame();
	application.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	application.setLocation(200, 100);
	application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	application.setLayout(null);
	application.setResizable(false);
	application.add(new Calendrier());
	application.setVisible(true);

    }

    /**
     * @brief classe s'occupant de la navigation entre les differents
     *        "components" du menu
     * */
    class CalenderNavigation extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton title;
	private final JButton next, previous;
	private final Color button_color = new Color(230, 230, 230);

	public CalenderNavigation() {

	    this.setSize(WINDOWS_WIDTH - 20, NAVIGATION_HEIGHT);
	    this.setLocation(6, 0);
	    GridLayout navigationLayout = new GridLayout(1, 3);

	    navigationLayout.setHgap(10);
	    this.setLayout(navigationLayout);

	    next = new JButton("next");
	    next.setBackground(button_color);
	    next.setSize(WINDOWS_WIDTH, NAVIGATION_HEIGHT);

	    previous = new JButton("previous");
	    previous.setSize(WINDOWS_WIDTH / 2 - 20, NAVIGATION_HEIGHT);
	    previous.setBackground(button_color);

	    title = new JButton();
	    title.setBackground(button_color);
	    title.setFont(Font.getFont(Font.SANS_SERIF));
	    title.setSize(38, NAVIGATION_HEIGHT);

	    previous.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    this.add(previous);
	    this.add(title);
	    this.add(next);

	}

	/**
     *
	 * @brief mise à jour du titre de la navigation ce titre est en haut de
	 *        la page
	 * 
	 * */
	public void setTitleField(String text) {
	    title.setText(text);

	}

	/**
	 * @brief modifie l'action associée au bouton "précedent" C'est le
	 *        bouton à gauche de la navigation
	 * */
	public void setPrevious(ActionListener actionListener) {
	    for (ActionListener al : previous.getActionListeners()) {
		previous.removeActionListener(al);
	    }
	    previous.addActionListener(actionListener);
	}

	/**
	 * @brief modifie l'action associée au bouton "suivant" c'est le bouton
	 *        à droite de la navigation.
	 * */
	public void setNext(ActionListener actionListener) {
	    for (ActionListener al : next.getActionListeners()) {
		next.removeActionListener(al);
	    }
	    next.addActionListener(actionListener);
	}

	/**
	 * @brief modifie l'action associée à l'appui sur le titre
	 * */
	public void setTitleAction(ActionListener actionListener) {
	    for (ActionListener al : title.getActionListeners()) {
		title.removeActionListener(al);
	    }
	    title.addActionListener(actionListener);
	}
    }
}
