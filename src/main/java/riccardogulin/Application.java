package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.entities.StudentType;
import riccardogulin.exceptions.NotFoundException;

public class Application {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d12pu");
	// Per connettersi al DB, dobbiamo sfruttare la Persistence Unit descritta nel file persistence.xml e chiamata u4d12pu
	// Per farlo dobbiamo creare un attributo statico di tipo EntityManagerFactory passandogli il nome della PU

	public static void main(String[] args) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		StudentsDAO studentsDAO = new StudentsDAO(entityManager);

		Student aldo = new Student("Aldo", "Baglio", StudentType.FULLTIME);
		Student giovanni = new Student("Giovanni", "Storti", StudentType.PARTTIME);
		Student giacomo = new Student("Giacomo", "Poretti", StudentType.FULLTIME);
//
//		studentsDAO.save(aldo);
//		studentsDAO.save(giovanni);
//		studentsDAO.save(giacomo);

		try {
			Student found = studentsDAO.findById(5);
			System.out.println(found);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			studentsDAO.findByIdAndDelete(2);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


	}
}
