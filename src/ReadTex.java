import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Diese Klasse uebersetzt Tex-kommentare zu Strucktogramme. Es wird
 * Tex-Kommentare zu Tex-Code Substituiert. Aus dem Substituierten Code wird
 * dann ein Strucktogramm entstehen. Die erlaubten Kommentaren sind: (tex.c) fur
 * Klassen (tex.m) fuer Methoden (tex.ret) fuer Return (tex.d.s)/(tex.d.e) fuer
 * Definitionen (tex.f.s)/(tex.f.e) fuer For-Schleifen (tex.w.s)/(tex.w.e) fuer
 * While-Schleifen (tex.if.s)/tex.if.e) fure if-Abfragen (tex.e) fuer
 * else-Bedingungen (tex.read) setzt naechste Zeile im Strukogramm.
 * 
 * @author Maher Albezem
 * 
 */
public class ReadTex {

	// Attribute
	private ArrayList<String> code = new ArrayList<String>(); // save the code line by line
	private ArrayList<String> texCode = new ArrayList<String>();
	private int zeile = 0;

	// Folgenden Methoden bekommen ein Scanner einer geoeffneten
	// Datei
	/**
	 * die Methode Speichert den Code in die Liste
	 * 
	 * @param sc
	 */
	public void ladeCode(Scanner sc) {
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			this.code.add(s);
		}
		sc.close();
	}

	/**
	 * die Methode schreibt den geaenderten Code in einer Datei
	 * 
	 * @param pw
	 */
	public void schreibeCode(PrintWriter pw) {
		// save the changed code
		for (int i = 0; i < this.texCode.size(); i++) {
			pw.println(this.texCode.get(i));
		}
		pw.close();
	}

	/**
	 * die Methode filtert die Kommentarzeilen, falls gewuenscht. Kann nicht rueckgaengig gemacht werden.
	 */
	public void entferneCode() {
		// remove code lines and leave tex-comments lines
		for (int i = 0; i < this.code.size(); i++) {
			if (this.code.get(i).contains("//tex") || this.code.get(i).contains("// tex")) {
				continue;
			}
			// remove code lines otherwise
			this.code.remove(i);
			--i;
		}
	}
	/**
	 * Hier wird Code rekursiv substituiert
	 * @throws FileNotFoundException
	 */
	public void substiutCode() throws FileNotFoundException {
		Write(new Scanner(new File("STRUKTEX\\kopf.tex")));

		searchAndSubElements(); // Starting with first line

		Write(new Scanner(new File("STRUKTEX\\fuss.tex")));
	}
	// Alle TeX-Dateien sind im Verzeichnis STRUKTEX
	private void searchAndSubElements() throws FileNotFoundException {
		String inhalt = "";
		String name1 = "";
		int start = 0;
		boolean isElse = false;
		for (; zeile < this.code.size() - 1; zeile++) {
			try {
				if (this.code.get(zeile).contains("//tex") || this.code.get(zeile).contains("// tex")) {
					String[] s = this.code.get(zeile).split("\\.");
					switch (s[1]) {
					case "c":// class
						inhalt = "Class ausfuellen";
						Write(new Scanner(new File("STRUKTEX/class.tex")), inhalt, "Beschreibung der Klasse");
						break;
					case "m":// method
						inhalt = "Method ausfuellen";
						Write(new Scanner(new File("./STRUKTEX/method.tex")), inhalt);
						break;
					case "d":// definition erfordert start und end
						if (s[2].equals("s")) {
							start = zeile;
						}
						if (s[2].equals("e") && start != 0) {
							for (int j = start + 1; j < zeile; j++) {
								inhalt = "ausfuellen";
								name1 = "ausfuellen";
								WriteDefinition(new Scanner(new File("./STRUKTEX/define.tex")), inhalt, name1);
							}
						}
						break;
					case "f":// for erfordert start und end
						if (s[2].equals("e")) {
							Write(new Scanner(new File("./STRUKTEX/fore.tex")));
							return;
						}
						Write(new Scanner(new File("./STRUKTEX/fors.tex")), "For Schleife");
						this.zeile++;
						searchAndSubElements();
						break;
					case "ret":
						inhalt = "Return ausfuellen";
						Write(new Scanner(new File("./STRUKTEX/return.tex")), inhalt);
						break;
					case "w": // while erfordert start und end
						if (s[2].equals("e")) {
							Write(new Scanner(new File("./STRUKTEX/whilee.tex")));
							return;
						}
						inhalt = "While Scleife";
						Write(new Scanner(new File("./STRUKTEX/whiles.tex")), inhalt);
						this.zeile++;
						searchAndSubElements();
						break;
					case "if": // erfordert start und end
						if (s[2].equals("e")) {
							if (!isElse) { // damit \change nicht vergessen wird, sonst wird Struktogramm falsch
											// aussehen
								Write(new Scanner(new File("./STRUKTEX/else.tex")));
							}
							Write(new Scanner(new File("./STRUKTEX/ife.tex")));
							return;
						}
						Write(new Scanner(new File("./STRUKTEX/ifs.tex")), "ausfuellen");
						this.zeile++;
						searchAndSubElements();
						break;
					case "e": // erfordert start und end
						if (s[2].equals("s")) {
							isElse = true;
							Write(new Scanner(new File("./STRUKTEX/else.tex")));
						}
						break;
					case "read":
						inhalt = this.code.get(zeile + 1);
						Write(new Scanner(new File("./STRUKTEX/read.tex")), inhalt);
						break;
					default:
						throw new WrongElementException("");

					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Falscher Tex-Kommentar in Zeile " + (this.zeile + 1) + "\nBeenden",
						"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} catch (WrongElementException e) {
				JOptionPane.showMessageDialog(null, "Falscher Tex-Kommentar in Zeile " + (this.zeile + 1) + "\nBeenden",
						"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
	}
	
	// Methoden zum schreiben in TeX-Dateien 
	private void Write(Scanner sc) {
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			this.texCode.add(s);
		}
		sc.close();
	}

	private void Write(Scanner sc, String name) {
		int line = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			line++;
			if (line == 1) {
				this.texCode.add(s + name);
				continue;
			}

			this.texCode.add(s);

		}
		sc.close();
	}

	private void Write(Scanner sc, String name, String beschreibung) {
		int line = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			line++;
			if (line == 4) {
				this.texCode.add(s + name);
				continue;
			}
			if (line == 5) {
				this.texCode.add(s + beschreibung);
				continue;
			}

			this.texCode.add(s);

		}
		sc.close();
	}

	private void WriteDefinition(Scanner sc, String name1, String name2) {
		int line = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			line++;
			if (line == 1) {
				this.texCode.add(s + name1);
				continue;
			}
			if (line == 2) {
				this.texCode.add(s + name2);
				continue;
			}

			this.texCode.add(s);

		}
		sc.close();
	}

}
