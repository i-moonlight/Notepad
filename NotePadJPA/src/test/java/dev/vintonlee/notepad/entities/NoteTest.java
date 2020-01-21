package dev.vintonlee.notepad.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoteTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Note note;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("NotePadPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		note = em.find(Note.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		note = null;
	}

	@Test
	@DisplayName("Test primary fields")
	void test() {
		assertEquals(1, note.getId());
		assertEquals("jobs", note.getTitle());
		assertEquals("apply to a lot of jobs", note.getText());
		assertTrue(note.getStarred());
		assertNull(note.getCreatedAt());
	}

}
