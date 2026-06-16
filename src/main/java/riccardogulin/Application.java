package riccardogulin;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d12pu");
	// Per connettersi al DB, dobbiamo sfruttare la Persistence Unit descritta nel file persistence.xml e chiamata u4d12pu
	// Per farlo dobbiamo creare un attributo statico di tipo EntityManagerFactory passandogli il nome della PU

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
