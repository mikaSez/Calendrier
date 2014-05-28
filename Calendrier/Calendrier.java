package Calendrier;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calendrier extends JPanel {

    public static final int WINDOWS_WIDTH = 700; // taille appli - le menu
    public static final int WINDOWS_HEIGHT = 530;
    public static final int NAVIGATION_HEIGHT = 50;
    public static final int MAIN_HEIGHT = 430;

    private static final long serialVersionUID = 4325406721569230614L;
    private final PanelBuilder pb;

    public Calendrier() {

	this.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	this.setLayout(null);
	CalenderNavigation cn = new CalenderNavigation();
	this.add(cn);
	pb = PanelBuilder.getBuilder(cn, this);
	this.add(pb.setToYearly().getForShow());

    }

    public static void main(String[] args) {
	JFrame application = new JFrame();
	application.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	application.setLocation(200, 100);
	application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	private final JLabel title;
	private final JButton next, previous;

	public CalenderNavigation() {

	    this.setSize(WINDOWS_WIDTH, NAVIGATION_HEIGHT);
	    this.setBorder(BorderFactory.createLineBorder(Color.black));
	    next = new JButton("next");
	    previous = new JButton("previous");
	    this.add(previous);
	    title = new JLabel();
	    title.setFont(Font.getFont(Font.SANS_SERIF));
	    this.add(title);
	    this.add(next);
	    ;

	}

	public void setTitleField(String text) {
	    title.setText(text);

	}

	public void setPrevious(ActionListener actionListener) {
	    for (ActionListener al : previous.getActionListeners()) {
		previous.removeActionListener(al);
	    }
	    previous.addActionListener(actionListener);
	}

	public void setNext(ActionListener actionListener) {
	    for (ActionListener al : next.getActionListeners()) {
		next.removeActionListener(al);
	    }
	    next.addActionListener(actionListener);
	}
    }
}
