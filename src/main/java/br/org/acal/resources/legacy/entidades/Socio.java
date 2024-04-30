package br.org.acal.resources.legacy.entidades;

import br.org.acal.domain.entity.Partner;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "socio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Socio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "carneDiferenciado")
    private Boolean carneDiferenciado;

    @Column(name = "SocioExclusivo")
    private Boolean socioExclusivo;

    @Basic(optional = false)
    @Column(name = "numeroSocio")
    private int numeroSocio;

    @Column(name = "dataVence")
    @Temporal(TemporalType.DATE)
    private Date dataVence;

    @Column(name = "dataAprovacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovacao;

    @Basic(optional = false)
    @Column(name = "dataMatricula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMatricula;

    @Lob
    @Column(name = "observacao")
    private String observacao;

    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Pessoa idPessoa;

    @JoinColumn(name = "idCategoriaSocio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoriaSocio idCategoriaSocio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCedente")
    private List<Entrada> entradaList;

}
