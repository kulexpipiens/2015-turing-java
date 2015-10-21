public class Main {

	private static String vstup = "abaa";

	private static class Instrukcia {
		Instrukcia(int staryStav, char povodnyZnak, char novyZnak, char zmena,
				int novyStav) {
			this.staryStav = staryStav;
			this.povodnyZnak = povodnyZnak;
			this.novyZnak = novyZnak;
			this.zmena = zmena;
			this.novyStav = novyStav;
		}

		private int staryStav;
		private char povodnyZnak;
		private char novyZnak;
		private char zmena;
		private int novyStav;
	}

	private static Instrukcia[] instrukcie = new Instrukcia[200];

	private static int pocetStavov = 50;

	public static void main(String[] args) {
		// pridame instrukcie do zoznamu instrukcii
		pridajInstrukcie();

		int indexHlavy = 0;
		char aktualnyZnak = '0';
		int aktualnyStav = 0;
		char paska[] = new char[200];
		// nainicializujeme pasku nulami
		dajNuly(paska);
		// vlozime vstup do pasky
		dajSlovoDoPasky(vstup, paska);

		// pokial mame instrukciu tak ju pouzijeme
		while (instrukcie[aktualnyStav + pocetStavov
				* posunutiePreZnak(aktualnyZnak)] != null) {
			Instrukcia praveUplatnovana = instrukcie[aktualnyStav + pocetStavov
					* posunutiePreZnak(aktualnyZnak)];

			// zmenim znak
			paska[indexHlavy] = praveUplatnovana.novyZnak;
			// zmenim stav
			aktualnyStav = praveUplatnovana.novyStav;
			// posuniem hlavu
			indexHlavy = indexHlavy + posunPodlaZmeny(praveUplatnovana.zmena);
			// zistim aktualny znak
			aktualnyZnak = paska[indexHlavy];
		}

		// zobrazim vysledok
		vypisPasku(paska);
	}

	private static void dajNuly(char[] paska) {
		for (int i = 0; i < paska.length; i++) {
			paska[i] = '0';
		}

	}

	/**
	 * Vlozi intrukcie do globalnej premennej instrukcii (na "vhodne" miesto)
	 */
	private static void pridajInstrukcie() {
		// prv obratim retazec
		Instrukcia nova = new Instrukcia(0, '0', '0', 'R', 8);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(8, 'a', 'a', 'R', 8);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(8, 'b', 'b', 'R', 8);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(8, '0', '0', 'L', 9);
		pridajDoZoznamu(nova);
		// iba posuniem posledne pismeno
		nova = new Instrukcia(9, 'a', '0', 'R', 10);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(9, 'b', '0', 'R', 11);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(10, '0', 'a', 'L', 31);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(11, '0', 'b', 'L', 31);
		pridajDoZoznamu(nova);

		// zistim, ci nebolo jedine pismenko
		nova = new Instrukcia(31, '0', '0', 'L', 32);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(32, 'a', 'a', 'R', 12);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(32, 'b', 'b', 'R', 12);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(32, '0', '0', 'R', 7);
		pridajDoZoznamu(nova);

		// idem prehadzovat pismenka
		// ale prv vyriesim, ci uz nemam len jedno pismenko
		nova = new Instrukcia(12, '0', '0', 'L', 13);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(13, '0', '0', 'N', 2);
		pridajDoZoznamu(nova);

		// ak 13 je a
		nova = new Instrukcia(13, 'a', 'a', 'L', 14);
		pridajDoZoznamu(nova);
		// zistim, ci nie je posledne pismenko
		nova = new Instrukcia(14, '0', '0', 'R', 16);
		pridajDoZoznamu(nova);
		// je posledne a je a, idem ho zapisat
		nova = new Instrukcia(16, 'a', '0', 'R', 17);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(17, '0', '0', 'R', 17);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(17, 'a', 'a', 'R', 18);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(17, 'b', 'b', 'R', 18);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(18, 'a', 'a', 'R', 18);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(18, 'b', 'b', 'R', 18);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(18, '0', 'a', 'N', 30);
		pridajDoZoznamu(nova);
		// nie je posledne, ale je a, idem zapisat
		nova = new Instrukcia(14, 'a', 'a', 'R', 15);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(14, 'b', 'b', 'R', 15);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(15, 'a', '0', 'R', 19);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(19, '0', '0', 'R', 19);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(19, 'a', 'a', 'R', 20);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(19, 'b', 'b', 'R', 20);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(20, 'a', 'a', 'R', 20);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(20, 'b', 'b', 'R', 20);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(20, '0', 'a', 'L', 21);
		pridajDoZoznamu(nova);
		// idem naspat
		nova = new Instrukcia(21, 'a', 'a', 'L', 21);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(21, 'b', 'b', 'L', 21);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(21, '0', '0', 'L', 22);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(22, '0', '0', 'L', 22);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(22, 'a', 'a', 'N', 13);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(22, 'b', 'b', 'N', 13);
		pridajDoZoznamu(nova);

		// ak 13 je b
		nova = new Instrukcia(13, 'b', 'b', 'L', 23);
		pridajDoZoznamu(nova);
		// zistim, ci nie je posledne pismenko
		nova = new Instrukcia(23, '0', '0', 'R', 24);
		pridajDoZoznamu(nova);
		// je posledne a je b, idem ho zapisat
		nova = new Instrukcia(24, 'b', '0', 'R', 25);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(25, '0', '0', 'R', 25);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(25, 'a', 'a', 'R', 26);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(25, 'b', 'b', 'R', 26);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(26, 'a', 'a', 'R', 26);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(26, 'b', 'b', 'R', 26);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(26, '0', 'b', 'N', 30);
		pridajDoZoznamu(nova);
		// nie je posledne, ale je b, idem zapisat
		nova = new Instrukcia(23, 'a', 'a', 'R', 27);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(23, 'b', 'b', 'R', 27);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(27, 'b', '0', 'R', 28);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(28, '0', '0', 'R', 28);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(28, 'a', 'a', 'R', 29);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(28, 'b', 'b', 'R', 29);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(29, 'a', 'a', 'R', 29);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(29, 'b', 'b', 'R', 29);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(29, '0', 'b', 'L', 21);
		pridajDoZoznamu(nova);

		nova = new Instrukcia(30, 'a', 'a', 'L', 30);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(30, 'b', 'b', 'L', 30);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(30, '0', '0', 'N', 7);
		pridajDoZoznamu(nova);

		/*
		 * pouzijem instrukcie z hodiny na ww^-1, kde w 
		 * priradim w^-1 a teda ziskam 
		 * (w^-1)(w^-1)^-1 = w^-1w
		 */
		nova = new Instrukcia(7, '0', '0', 'R', 1);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(1, 'a', 'a', 'R', 1);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(1, 'b', 'b', 'R', 1);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(1, '0', '0', 'L', 2);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(2, 'a', '0', 'R', 3);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(2, 'b', '0', 'R', 4);
		pridajDoZoznamu(nova);

		nova = new Instrukcia(3, 'a', 'a', 'R', 3);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(3, 'b', 'b', 'R', 3);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(3, '0', 'a', 'L', 5);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(5, 'a', 'a', 'L', 5);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(5, 'b', 'b', 'L', 5);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(5, '0', 'a', 'L', 2);
		pridajDoZoznamu(nova);

		nova = new Instrukcia(4, 'a', 'a', 'R', 4);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(4, 'b', 'b', 'R', 4);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(4, '0', 'b', 'L', 6);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(6, 'a', 'a', 'L', 6);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(6, 'b', 'b', 'L', 6);
		pridajDoZoznamu(nova);
		nova = new Instrukcia(6, '0', 'b', 'L', 2);
		pridajDoZoznamu(nova);

	}

	private static void pridajDoZoznamu(Instrukcia nova) {
		instrukcie[nova.staryStav + pocetStavov
				* posunutiePreZnak(nova.povodnyZnak)] = nova;
	}

	private static int posunPodlaZmeny(char zmena) {
		if (zmena == 'R') {
			return 1;
		} else if (zmena == 'L') {
			return -1;
		} else {
			return 0;
		}
	}

	private static int posunutiePreZnak(char c) {
		if (c == '0') {
			return 0;
		} else if (c == 'a') {
			return 1;
		} else {
			return 2;
		}
	}

	private static void dajSlovoDoPasky(String string, char[] paska) {
		for (int i = 1; i <= string.length(); i++) {
			paska[i] = string.charAt(i - 1);
		}

	}

	private static void vypisPasku(char[] paska) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paska.length; i++) {
			if (paska[i] != '0') {
				sb.append(paska[i]);
			}
		}

		System.out.println(sb.toString());

	}
}
