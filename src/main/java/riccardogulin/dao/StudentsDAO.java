package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class StudentsDAO {
	// DAO = Data Access Object
	// E' un'astrazione, cioè, siccome le interazioni con il DB richiedono un po' di righe
	// di codice non particolarmente semplice, creiamo questa classe che fornirà dei metodi
	// semplici da usare nel main nascondendo queste complessità

	private final EntityManager entityManager;


	// Tutti i metodi di questo DAO avranno bisogno di utilizzare l'EntityManager poichè é l'oggetto che mi consente
	// di salvare, cancellare, leggere, sincronizzarmi col DB. Siccome l'oggetto entity manager viene creato nel main
	// è comodo passarlo come parametro del costruttore del DAO in maniera da avercelo già a disposizione in tutti i suoi metodi
	public StudentsDAO(EntityManager em) {
		this.entityManager = em;
	}

	public void save(Student newStudent) {
		// Entity Manager quando facciamo modifiche esige una transazione
		// 1. Creiamo una transazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		// 2. Facciamo partire la transazione
		transaction.begin();

		// 3. Siccome newStudent non è MANAGED, per aggiungerlo all'elenco degli oggetti
		// monitorati (PersistenceContext) dobbiamo effettuare un'operazione di PERSIST
		// L'oggetto managed però ancora parte del DB
		this.entityManager.persist(newStudent);

		// 4. L'operazione di COMMIT sincronizza il PersistenceContext con il DB
		// Siccome in questo caso c'è un oggetto nuovo nel PC, creerà una nuova riga nella tabella students
		transaction.commit();

		// 5. Log di avvenuto salvataggio
		System.out.println("Lo studente " + newStudent + " è stato salvato nel DB!");

	}

	public Student findById(long id) {
		Student fromDB = this.entityManager.find(Student.class, id); // Se non trova niente mi torna NULL
		if (fromDB == null) throw new NotFoundException(id);
		return fromDB;
	}

	public void findByIdAndDelete(long id) {
		// 1. Cerchiamo lo studente tramite id (potremmo riciclare il metodo findById che gestisce anche con eccezione custom)
		Student fromDB = this.findById(id);

		// 2. Creiamo una transazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		// 3. Facciamo partire la transazione
		transaction.begin();

		// 4. Informiamo l'EntityManager che lo studente è da cancellare dal DB, lo facciamo tramite metodo .remove()
		this.entityManager.remove(fromDB);

		// 5. L'operazione di COMMIT sincronizza il PersistenceContext con il DB
		// Siccome in questo caso c'è un oggetto segnato da rimuovere, il DB cancellerà la riga corrispondente
		transaction.commit();

		// 6. Log di avvenuta cancellazione
		System.out.println("Lo studente " + fromDB + " è stato rimosso dal DB!");
	}

}
