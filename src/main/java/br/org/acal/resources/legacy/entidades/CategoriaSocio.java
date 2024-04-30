package br.org.acal.resources.legacy.entidades;

import br.org.acal.resources.model.PartnerModel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categoriasocio")
@XmlRootElement

public class CategoriaSocio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    
    @JoinColumn(name = "taxasId", referencedColumnName = "id")
    @ManyToOne
    private Taxa taxasId;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaSocio")
    private List<PartnerModel> partnerModelList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaSocio")
    private List<EnderecoPessoa> enderecopessoaList;
  
    public CategoriaSocio() {
    }

    public CategoriaSocio(Integer id) {
        this.id = id;
    }

    public CategoriaSocio(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Taxa getTaxasId() {
        return taxasId;
    }

    public void setTaxasId(Taxa taxasId) {
        this.taxasId = taxasId;
    }

    @XmlTransient
    public List<PartnerModel> getSocioList() {
        return partnerModelList;
    }

    public void setSocioList(List<PartnerModel> partnerModelList) {
        this.partnerModelList = partnerModelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaSocio)) {
            return false;
        }
        CategoriaSocio other = (CategoriaSocio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    @XmlTransient
    public List<EnderecoPessoa> getEnderecopessoaList() {
        return enderecopessoaList;
    }

    public void setEnderecopessoaList(List<EnderecoPessoa> enderecopessoaList) {
        this.enderecopessoaList = enderecopessoaList;
    }
    
}
