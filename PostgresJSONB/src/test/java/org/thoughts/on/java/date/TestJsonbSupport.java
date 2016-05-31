package org.thoughts.on.java.date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.thoughts.on.java.model.MyEntity;
import org.thoughts.on.java.model.MyJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJsonbSupport {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@Test
	public void testJsonMapping() throws JsonProcessingException {
		log.info("... testJsonMapping ...");

		MyJson j = new MyJson();
		j.setLongProp(123L);
		j.setStringProp("abc");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(j));
	}
	
	@Test
	public void testCreateJsonbEntity() {
		log.info("... testCreateJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyJson j = new MyJson();
		j.setLongProp(123L);
		j.setStringProp("abc");
		
		MyEntity e = new MyEntity();
		e.setJsonProperty(j);
		em.persist(e);
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testUpdateJsonbEntity() {
		log.info("... testUpdateJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyEntity e = em.find(MyEntity.class, 10000L);
			
		e.getJsonProperty().setStringProp("changed");
		e.getJsonProperty().setLongProp(789L);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testSelectJsonbEntity() {
		log.info("... testSelectJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyEntity e = (MyEntity) em.createNativeQuery("SELECT * FROM myentity e WHERE e.jsonproperty->'longProp' = '456'", MyEntity.class).getSingleResult();
		
		Assert.assertNotNull(e.getJsonProperty());
		System.out.println("JSON: stringProp = "+e.getJsonProperty().getStringProp()+"    longProp = "+e.getJsonProperty().getLongProp());
		
		em.getTransaction().commit();
		em.close();
	}
}
