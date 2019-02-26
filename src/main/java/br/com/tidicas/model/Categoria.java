package br.com.tidicas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe para implementar a tabela categoria no banco de dados 
 * 
 * @author Evaldo Junior
 */
@Entity
@Table(name="categoria")
@NamedQueries({
  @NamedQuery( name = "categoriaByBlogId", query = "select c from Categoria c where c.codigo = :codigo")
})
public class Categoria implements Serializable {
  private static final long serialVersionUID = 1L;
   
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String codigo;
  
	private String descricao;
	
	@ManyToOne  
  private Blog blog;
    
	public Categoria() {
		super();
	}   
	
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}   
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
  public Blog getBlog() {
    return blog;
  }
  public void setBlog(Blog blog) {
    this.blog = blog;
  }
	   
}