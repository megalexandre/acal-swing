package br.org.acal.resources.legacy.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "taxa")
@XmlRootElement

public class Taxa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Lob
    @Column(name = "observacao")
    private String observacao;
    
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "valor_socio")
    private BigDecimal valorSocio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxaid")
    private List<Taxasconta> taxascontaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxasId")
    private List<CategoriaSocio> categoriasocioList;

    public Taxa() {
    }

    public Taxa(Integer id) {
        this.id = id;
    }

    public Taxa(Integer id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorSocio(){
        return valorSocio;
    }
    
    public void setValorSocio( BigDecimal valorSocio){
        this.valorSocio = valorSocio;
    }
            
    @XmlTransient
    public List<Taxasconta> getTaxascontaList() {
        return taxascontaList;
    }

    public void setTaxascontaList(List<Taxasconta> taxascontaList) {
        this.taxascontaList = taxascontaList;
    }

    @XmlTransient
    public List<CategoriaSocio> getCategoriasocioList() {
        return categoriasocioList;
    }

    public void setCategoriasocioList(List<CategoriaSocio> categoriasocioList) {
        this.categoriasocioList = categoriasocioList;
    }
    
}
