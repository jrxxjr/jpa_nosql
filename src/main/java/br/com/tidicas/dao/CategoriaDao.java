package br.com.tidicas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.tidicas.model.Categoria;

/**
 * Classe para manipular os funcionalidades de banco de dados da tabela categoria
 * 
 * @author Evaldo Junior
 */
public class CategoriaDao {
  private final Dao<Categoria> dao;
  private final EntityManager em;
  

  public CategoriaDao(EntityManager em) {
    this.dao = new Dao<Categoria>(em, Categoria.class);
    this.em = em;
  }

  public void adiciona(Categoria categoria) {
    this.dao.adiciona(categoria);
  }

  public void remove(Categoria categoria) {
    this.dao.remove(categoria);
  }

  public void atualiza(Categoria categoria) {
    this.dao.atualiza(categoria);
  }

  public List<Categoria> lista() {
    return this.dao.lista();
  }

  public Categoria busca(String id) {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("codigo", id);
    return dao.findOneResult("categoriaByBlogId", parameters);
  }
}
