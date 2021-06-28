import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Startet eine Uhrenoberfläche
 * @author Doro
 *
 */
public class Starter extends JFrame{
	private static final long serialVersionUID = 1L;
	private List<DigitalUhr> dUhren = new LinkedList<>();
	private List<KreisUhr> kUhren = new LinkedList<>();
	
	/**
	 * erzeugt die Oberfläche und bringt sie auf den Bildschirm
	 */
	public Starter()
	{
		setTitle("Uhren-Anzeiger");
		setSize(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		// der Button btnKreis erzeugt eine KreisUhr
		addButton("Kreis", e -> kUhren.add(new KreisUhr()));
		
		//der Button btnDigital erzeugt eine DigitalUhr
		addButton("Digital", e -> dUhren.add(new DigitalUhr())); 
		
		// der Button btnHalt löscht alle Uhranzeige-Fenster vom
		//Bildschirm und leert die beiden Listen
		addButton("alle entfernen", e -> {for(KreisUhr k : kUhren) k.dispose();
			for(DigitalUhr d : dUhren) d.dispose();
			kUhren.clear();
			dUhren.clear();
	  	});

		// Auf den Bildschirm bringen:
		pack();
		setVisible(true);
	}

	public void addButton(String name, ActionListener e){
		JButton btn = new JButton(name);
		btn.addActionListener(e);
		add(btn);
	}

	public static void main(String[] args) {
		new Starter();
	}

}
