package br.com.tidicas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe correspondente a tabela blog do banco de dados
 * 
 * @author Evaldo Junior
 *
 */

@Entity
@Table(name = "blog")
@NamedQueries({
  @NamedQuery( name = "blogById", query = "select b from Blog b where b.codigo = :codigo")
  })
public class Blog implements Serializable {

  private static final long serialVersionUID = 7551953961461778499L;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")  private String codigo;
  private String conteudo;

  private String titulo;
  @Temporal(TemporalType.DATE)
  private Date dtevento;
  private Integer contador;
  private Integer publicar;

  @OneToMany(mappedBy="blog")  
  public Set<Categoria> categorias = new HashSet<Categoria>();
  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Date getDtevento() {
    return dtevento;
  }

  public void setDtevento(Date dtevento) {
    this.dtevento = dtevento;
  }

  public Integer getContador() {
    return contador;
  }

  public void setContador(Integer contador) {
    this.contador = contador;
  }

  public Integer getPublicar() {
    return publicar;
  }

  public void setPublicar(Integer publicar) {
    this.publicar = publicar;
  }

  public Set<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(Set<Categoria> categorias) {
    this.categorias = categorias;
  }

}