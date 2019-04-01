
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Version: 1, letzte Aenderung am: 28.11.2017. Berschreibung: Die Klasse
 * Primfaktorzerlegung ist die Darstellung einer natürlichen Zahl als Produkt
 * aus Primzahlen.
 * 
 * @author Maher Albezem
 *
 */
//tex.c
public class Primfaktorzerlegung {
	/**
	 * die Main Methode zeigt die Eingabe und fragt nach einer Zahl zum eingeben.
	 * schließlich wird die Zahl faktorisiert.
	 * 
	 */
	//tex.m
	public static void main(String[] args) {
		//Show input menu
		boolean exit = false;
		int input = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Primfaktorzerlegung ist die Darstellung einer natürlichen Zahl als Produkt\n aus Primzahlen.");
		//tex.w.s
		while (!exit) {
			//Show input menu
			System.out.println("\n\nGeben Sie eine Zahl ein(1 bis 10^3), um ihre Primfaktorzerlegung zu bestimmen");
			System.out.println("Eingabe ->");
			try {
				input = Integer.parseInt(sc.next());
				if (input < 0 || input > 1000000) {
					throw new NumberFormatException("");
				}
			} catch (NumberFormatException e) {
				System.out.println("Falsche Eingabe");
				continue;
			}
			int temp = input;
			// get all prime numbers and test
			//tex.f.s
			for (int primzahl : getPrimzahlen(input)) {
				//tex.w.s
				while (true) {
					//tex.if.s
					if (temp % primzahl == 0) {
						result.add(primzahl);
						temp = temp / primzahl;
					}//tex.e.s
					else {
						break;
					}//tex.e.e
					//tex.if.e
				}//tex.w.e
			}//tex.f.e
			// show output
			System.out.println("Faktoren von " + input);
			System.out.print(input + " = ");
			//tex.f.s
			for (int faktoren : result) {
				System.out.print("" + faktoren + " . ");
				
			}//tex.f.e
			//tex.read
			// clear all list elements
			result.clear();
		}//tex.w.e
	}
	/**
	 * Die Methode berechnet alle Primzahlen <= grenze
	 * @param grenze
	 * @return Liste der Primzahlen
	 */
	//tex.m.s
	public static ArrayList<Integer> getPrimzahlen(int grenze) {
		// get all prime numbers and disclude eaven numbers since they are not primals
		
		ArrayList<Integer> primzahlen = new ArrayList<Integer>();
		primzahlen.add(2);
		
		//tex.f.s
		for (int i = 2; i <= grenze; i++) {
			//tex.if.s
			if (i % 2 == 0) {
				continue;
			}
			//tex.if.e
			//tex.d.s
			int test = 3;
			//tex.d.e
			//tex.w.s
			while (test * test < i & i % test != 0) {
				//tex.d.s
				test += 2;
				//tex.d.e
			}
			//tex.w.e
			//tex.if.s
			if (test * test > i) {
				primzahlen.add(i);
			}
			//tex.if.e
		}//tex.f.e
		//tex.ret
		return primzahlen;
	}
}
//tex-e-class-Primfaktorzerlegung
