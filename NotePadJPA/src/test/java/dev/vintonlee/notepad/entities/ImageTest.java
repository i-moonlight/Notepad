package dev.vintonlee.notepad.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Image image;

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
		image = em.find(Image.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		image = null;
	}

	@Test
	@DisplayName("Test primary fields")
	void test1() {
//		select * from image where id = 1;
		assertEquals(1, image.getId());
		assertTrue(image.getUrlLink().contains("https://i.imgur.com/HlVLzU9.jpg"));
	}

	@Test
	@DisplayName("Test relationship with user")
	void test2() {
//		select * from image where id = 1;
		assertEquals(1, image.getUser().getId());
	}

}
