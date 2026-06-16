package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // Annotazione OBBLIGATORIA per tutte le Entities. Serve per creare una mappatura tra questa classe
// ed una tabella corrispondente nel DB
// Se abbiamo usato l'impostazione   <property name="hibernate.hmb2ddl.auto" value="update"/> all'avvio dell'applicazione
// verrà rilevata questa Entity e verrà creata la tabella corrispondente, oppure se già esistente verrà aggiornata al bisogno
// Gli attributi di questa classe verranno mappati a colonne della tabella anche se non usiamo l'annotazione @Column

// N.B. Un errore molto molto comune è quello di dimenticarsi di aggiornare il persistence.xml con
// <class>riccardogulin.entities.Student</class>

@Table(name = "students") // <-- Annotazione OPZIONALE. Utile però per ad esempio customizzare il nome della tabella
public class Student {

	@Id // Annotazione OBBLIGATORIA! Serve per stabilire che questo campo sarà la CHIAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Annotazione OPZIONALE MA MOLTO CONSIGLIATA!
	// @GeneratedValue serve per chiedere a Postgres di generare automaticamente
	// l'id ad ogni inserimento. con strategy Identity gli chiediamo inoltre di rendere quel campo long invece che un biginteger un
	// bigserial, quindi intero grande autoincrementante
	@Column(name = "student_id")
	private long id;

	@Column(name = "first_name", nullable = false, length = 30)
	private String name;

	@Column(name = "last_name", nullable = false, length = 30)
	private String surname;

	@Column(name = "student_type", nullable = false)
	@Enumerated(EnumType.STRING) // Di default gli Enum vengono trattati come SMALLINT ma solitamente non è questo che vogliamo
	// Se voglio dire a Hibernate creami una colonna testuale devo usare @Enumerated(EnumType.STRING)
	private StudentType studentType;

	public Student() {
	} // Il costruttore vuoto è OBBLIGATORIO PER TUTTE LE ENTITIES!

	public Student(String name, String surname, StudentType studentType) {
		this.name = name;
		this.surname = surname;
		this.studentType = studentType;
	}


	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", studentType=" + studentType +
				'}';
	}
}
