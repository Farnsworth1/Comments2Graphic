
//tex.c.s
public class Fakultaet {
	/**
	 * 
	 * @param n
	 * @return berechnet zu dem uebergebenen n die Fakultaet, zur Berechnungen wird
	 *         eine Variable vom Typ int verwendet
	 */
	// tex.m
	public static int berechneFakultaetInteger(int n) {
		// tex.d.s
		int result = 1;
		// tex.d.e
		// tex.f.s
		for (int i = n; i >= 1; i--) {
			// tex.d.s
			result = result * i;
			// tex.d.e
		}
		// tex.f.e
		// tex.ret
		return result;
	}

	// tex.m
	public static long berechneFakultaetLong(int n) {
		// tex.d.s
		long result = 1;
		// tex.d.e
		// tex.f.s
		for (int i = n; i >= 1; i--) {
			// tex.d.s
			result = result * i;
			// tex.d.e
		}
		// tex.f.e
		// tex.ret
		return result;
	}

	// tex.m
	public static void test(int n) {
		System.out.println("berechneFakutaetInteger(" + n + ") = " + berechneFakultaetInteger(n));

		System.out.println("berechneFakutaetLong(" + n + ") = " + berechneFakultaetLong(n));

	}

	// tex.m
	public static void main(String[] args) {
		Fakultaet.test(5);
		Fakultaet.test(10);
		Fakultaet.test(20);
		Fakultaet.test(50);
		Fakultaet.test(100);
		Fakultaet.test(1000);

	}
}// tex.c.e
