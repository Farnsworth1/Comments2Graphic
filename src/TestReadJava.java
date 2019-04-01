
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Test Klasse fuer die Klasse ReadJava.java
 * 
 * @author m_albezem
 *
 */
public class TestReadJava {
	public static void main(String[] args) throws FileNotFoundException {
		File file = null;
		// Dateiauswahlfenster auf derzeitigen Projektordner
		// Aenderungen werden ueberschriben
		JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
		int state = fc.showOpenDialog(null);
		if (state == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		} else {
			System.out.println("Auswahl abgebrochen");
		}
		Scanner sc = new Scanner(file);
		PrintWriter pw = new PrintWriter(file.getName() + ".tex");
		ReadJavaTex myCode = new ReadJavaTex();
		myCode.ladeCode(sc);
		// myCode.entferneCode();
		myCode.substiutCode();
		myCode.schreibeCode(pw);
		sc.close();
		pw.close();
	}
}
