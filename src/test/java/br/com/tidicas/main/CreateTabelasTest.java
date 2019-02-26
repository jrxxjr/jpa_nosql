package br.com.tidicas.main;

import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.tidicas.dao.BlogDao;
import br.com.tidicas.dao.CategoriaDao;
import br.com.tidicas.dao.JpaUtil;
import br.com.tidicas.model.Blog;
import br.com.tidicas.model.Categoria;

/**
 * Classe para geracao das tabelas
 * 
 * @author Evaldo Junior
 *
 */
public class CreateTabelasTest {
  private static final Logger LOGGER = Logger.getLogger(CreateTabelasTest.class.getName());

  @Test
  public void testCrud() {

    EntityManager emMongoDb = JpaUtil.getEmMongoDb();
   
    if (emMongoDb != null) {
      fillData(emMongoDb);
    }

  }

  private void fillData(EntityManager em) {
    CategoriaDao daoCategoria = new CategoriaDao(em);
    BlogDao daoBlog = new BlogDao(em);

    // 1 Entidade Categoria
    Categoria c = new Categoria();    
    c.setDescricao("categoria new");

    em.getTransaction().begin();
    
    daoCategoria.adiciona(c);
    em.getTransaction().commit();

    c = daoCategoria.busca(c.getCodigo());
    LOGGER.info("retorno:" + c.getCodigo());

    em.getTransaction().begin();
    c.setDescricao("categoria update");
    daoCategoria.atualiza(c);
    em.getTransaction().commit();

    // 2 Entidade Blog
    Blog b = new Blog();    
    b.setConteudo("conteúdo teste");
    b.setDtevento(new Date());
    b.setPublicar(1);
    b.setTitulo("título");
    c.setBlog(b);
    
    b.setCategorias(new HashSet<Categoria>());    
    b.getCategorias().add(c);
    
    em.getTransaction().begin();
    daoBlog.adiciona(b);
    em.getTransaction().commit();

    b = daoBlog.busca(b.getCodigo());
    LOGGER.info("retorno:" + b.getTitulo());

    b.setConteudo("conteúdo teste update");
    b.setDtevento(new Date());
    b.setPublicar(0);
    b.setTitulo("título update");
    em.getTransaction().begin();
    daoBlog.atualiza(b);
    em.getTransaction().commit();

    b = daoBlog.busca(b.getCodigo().toString());
    LOGGER.info("retorno:" + b.getTitulo());

    em.getTransaction().begin();
    daoCategoria.remove(c);
    daoBlog.remove(b);
    em.getTransaction().commit();
    LOGGER.info("delete:" + b.getTitulo());
  }

}