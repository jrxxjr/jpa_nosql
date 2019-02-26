package br.com.tidicas.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
                  
/**
 * Classe genérica para manipular as funcionalidades de banco de dados 
 * 
 * @author Evaldo Junior
 */
public class Dao<T>{                                                   
   private final EntityManager em;                                     
   private final Class<T> classe;                                      
   private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
   
   public Dao(EntityManager em, Class<T> classe){                      
      this.em = em;                                                    
      this.classe = classe;                                            
   }                                                                   
                                                                       
   public T busca(String id){                                         
      return this.em.getReference(classe, id);                         
   }                                                                   
                                                                       
   public List<T> lista(){                                             
      return em.createQuery("from " + classe.getName()).getResultList();
   }                                                                   
                                                                       
   public void adiciona(T t){                                          
      this.em.persist(t);                                              
   }                                                                   
                                                                       
   public void remove(T t){                                            
      this.em.remove(t);                                               
   }
   public void atualiza(T t){                                          
	      this.em.merge(t);                                              
   }
   public void removeQuery(T t,Integer chave){                                            
	      this.em.createQuery("delete from " + classe.getName() + " where codigo = " + chave).executeUpdate();    
	      
   }
   
   @SuppressWarnings("unchecked")
   public T findOneResult(String namedQuery, Map<String, Object> parameters) {
     T result = null;

     try {
       Query query = em.createNamedQuery(namedQuery);

       if (parameters != null && !parameters.isEmpty()) {
         populateQueryParameters(query, parameters);
       }

       result = (T) query.getSingleResult();

     } catch (Exception e) {
       LOGGER.log(Level.SEVERE, "Erro enquanto executava query: ", e.getCause());
     }

     return result;
   }

   
   @SuppressWarnings("unchecked")
   public List<T> findResults(String namedQuery, Map<String, Object> parameters) {
     List<T> result = null;

     try {
       Query query = em.createNamedQuery(namedQuery);

       if (parameters != null && !parameters.isEmpty()) {
         populateQueryParameters(query, parameters);
       }

       result = query.getResultList();

     } catch (Exception e) {
       LOGGER.log(Level.SEVERE, "Erro enquanto executava query: ", e.getCause());
     }

     return result;
   }
   
   private void populateQueryParameters(Query query, Map<String, Object> parameters) {

     for (Entry<String, Object> entry : parameters.entrySet()) {
       query.setParameter(entry.getKey(), entry.getValue());
     }
   }
   
}