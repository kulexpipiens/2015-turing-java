import java.io.File;
import java.util.Scanner;

public class GeneratorInstrukciiZKodu {
	public static void main(String[] args) {
		try (Scanner s = new Scanner(new File("kod"))) {
			while (s.hasNextLine()) {
				String riadok = s.nextLine();
				analyzuj(riadok);
			}
		} catch (Exception e) {
			System.err.println("Pruser!");
		}
	}

	private static void analyzuj(String riadok) {
		Scanner s = new Scanner(riadok);
		if (s.next().equals("nova")) {
			vypis(riadok.substring(24, riadok.length() - 2));
		}
		s.close();
	}

	private static void vypis(String substring) {
		System.out.print("<s");

		Scanner s = new Scanner(substring);
		s.useDelimiter(", *");
		System.out.println(s.next() + ", " + s.next().substring(1, 2) + ", "
				+ s.next().substring(1, 2) + ", " + s.next().substring(1, 2)
				+ ", s" + s.next() +">,");

		s.close();
	}
}
