/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.acal.resources.legacy.entidades;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author megalexandre
 */
@Entity
@Table(name = "enderecopessoa")
@XmlRootElement

public class EnderecoPessoa implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnderecoPessoa")
    private List<Conta> contaList;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Numero")
    private String numero;
    @Column(name = "datamatricula")
    @Temporal(TemporalType.DATE)
    private Date datamatricula;
    @Basic(optional = false)
    @Column(name = "inativo")
    private boolean inativo;
    @Basic(optional = false)
    @Column(name = "socioExclusivo")
    private boolean socioExclusivo;
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco idEndereco;
    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa idPessoa;
    @JoinColumn(name = "idCategoriaSocio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoriaSocio idCategoriaSocio;

    public EnderecoPessoa() {
    }

    public EnderecoPessoa(Integer id) {
        this.id = id;
    }

    public EnderecoPessoa(Integer id, boolean inativo, boolean socioExclusivo) {
        this.id = id;
        this.inativo = inativo;
        this.socioExclusivo = socioExclusivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public Date getDatamatricula() {
        return datamatricula;
    }

    public void setDatamatricula(Date datamatricula) {
        Date oldDatamatricula = this.datamatricula;
        this.datamatricula = datamatricula;
        changeSupport.firePropertyChange("datamatricula", oldDatamatricula, datamatricula);
    }

    public boolean getInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        boolean oldInativo = this.inativo;
        this.inativo = inativo;
        changeSupport.firePropertyChange("inativo", oldInativo, inativo);
    }

    public boolean getSocioExclusivo() {
        return socioExclusivo;
    }

    public void setSocioExclusivo(boolean socioExclusivo) {
        boolean oldSocioExclusivo = this.socioExclusivo;
        this.socioExclusivo = socioExclusivo;
        changeSupport.firePropertyChange("socioExclusivo", oldSocioExclusivo, socioExclusivo);
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        Endereco oldIdEndereco = this.idEndereco;
        this.idEndereco = idEndereco;
        changeSupport.firePropertyChange("idEndereco", oldIdEndereco, idEndereco);
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        Pessoa oldIdPessoa = this.idPessoa;
        this.idPessoa = idPessoa;
        changeSupport.firePropertyChange("idPessoa", oldIdPessoa, idPessoa);
    }

    public CategoriaSocio getIdCategoriaSocio() {
        return idCategoriaSocio;
    }

    public void setIdCategoriaSocio(CategoriaSocio idCategoriaSocio) {
        CategoriaSocio oldIdCategoriaSocio = this.idCategoriaSocio;
        this.idCategoriaSocio = idCategoriaSocio;
        changeSupport.firePropertyChange("idCategoriaSocio", oldIdCategoriaSocio, idCategoriaSocio);
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
        if (!(object instanceof EnderecoPessoa)) {
            return false;
        }
        EnderecoPessoa other = (EnderecoPessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Enderecopessoa[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @XmlTransient
    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
    }
    
}
