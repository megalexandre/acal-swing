/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.resources.legacy.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexandre
 */
@Entity
@Table(name = "chequeslog")
@XmlRootElement

public class Chequeslog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "idOriginal")
    private Integer idOriginal;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "numero")
    private Integer numero;
    @Lob
    @Column(name = "observacoes")
    private String observacoes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "idFuncionarioAlteracao")
    private Integer idFuncionarioAlteracao;
    @Column(name = "idMotivoDepesa")
    private Integer idMotivoDepesa;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "usuariobanco")
    private String usuariobanco;

    public Chequeslog() {
    }

    public Chequeslog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOriginal() {
        return idOriginal;
    }

    public void setIdOriginal(Integer idOriginal) {
        this.idOriginal = idOriginal;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getIdFuncionarioAlteracao() {
        return idFuncionarioAlteracao;
    }

    public void setIdFuncionarioAlteracao(Integer idFuncionarioAlteracao) {
        this.idFuncionarioAlteracao = idFuncionarioAlteracao;
    }

    public Integer getIdMotivoDepesa() {
        return idMotivoDepesa;
    }

    public void setIdMotivoDepesa(Integer idMotivoDepesa) {
        this.idMotivoDepesa = idMotivoDepesa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuariobanco() {
        return usuariobanco;
    }

    public void setUsuariobanco(String usuariobanco) {
        this.usuariobanco = usuariobanco;
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
        if (!(object instanceof Chequeslog)) {
            return false;
        }
        Chequeslog other = (Chequeslog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Chequeslog[ id=" + id + " ]";
    }
    
}
