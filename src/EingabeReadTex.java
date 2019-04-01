import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Diese klasse fuehrt das Programm ReadTex aus. Codezeilen werden geladen,
 * substituiert und anschliessend geschrieben.
 * 
 * @author m_albezem
 *
 */
public class EingabeReadTex {
	public static void main(String[] args) throws FileNotFoundException {
		File file = null;
		Scanner scInput = new Scanner(System.in);
		String input;
		boolean exit = false;
		System.out.println("********************** STRUKTOGRAMM GENERATOR **********************");
		System.out.println("**Die Grundelemente des Struktogramms sind nach DIN 66261 genormt.**");
		System.out.println("********************************************************************");
		System.out.println("\n");
		while(!exit) {
			System.out.println("Menue:");
			System.out.println("- Datei zum auslesen waehlen (1)");
			System.out.println("- Beenden                    (0)");
			System.out.println("Eingabe ->");
			input = scInput.nextLine();
			switch(input) {
			case "1":
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
				ReadTex myCode = new ReadTex();
				myCode.ladeCode(sc);
//			    myCode.entferneCode();
				myCode.substiutCode();
				myCode.schreibeCode(pw);
				System.out.println("Datei "+file.getName() + ".tex"+" wurde erstellt!\n");
				sc.close();
				pw.close();
				break;
			case "0":
				System.out.println("STRUKTOGRAMM GENERATOR Beendet.");
				exit = true;
				break;
			default:
				System.out.println("Falsche Eingabe\n");
			}
		}
	}
}
