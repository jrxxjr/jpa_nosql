package br.com.tidicas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que controla as instâncias da conexão com o banco de dados 
 * 
 * @author Evaldo Junior
 */
public class JpaUtil {
	private static EntityManagerFactory emMongoDb = Persistence.createEntityManagerFactory("jpamongodb");
	
	public static EntityManager getEmMongoDb(){
		return emMongoDb.createEntityManager();
	}
}
