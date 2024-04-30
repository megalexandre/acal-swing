package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.CategoriaSocio;
import br.org.acal.resources.legacy.entidades.Endereco;
import br.org.acal.resources.legacy.entidades.EnderecoPessoa;
import br.org.acal.resources.legacy.entidades.Pessoa;
import br.org.acal.resources.legacy.entidades.Socio;
import br.org.acal.resources.legacy.entidades.Sociotabela;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoSocio {

    private final SessionFactory sessionFactory;

    public DaoSocio(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void AdicionarSocio(Socio partnerModel) {
        sessionFactory.getCurrentSession().persist(partnerModel);
    }

    public void AlterarSocio(Socio partnerModel) {
        sessionFactory.getCurrentSession().persist(partnerModel);
    }

    public void ApagarSocio(Socio partnerModel) {
        sessionFactory.getCurrentSession().remove(partnerModel);
    }

    public List<Socio> SociosPorNomeLike(String nome) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Socio s  where s.idPessoa.nome like :nome  ", Socio.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public Socio BuscarSocioId(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Socio s  where s.idPessoa.nome like :nome  ", Socio.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Socio> SocioPorRua(String rua) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Socio s, Pessoa p where p.id = s.idPessoa", Socio.class)
                .getResultList();
    }

    public List<Integer> TodosOsSociosPorId() {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from Socio", Integer.class)
                .getResultList();
    }

    public List<Socio> TodosOsSocios() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Socio", Socio.class)
                .getResultList();

    }

    public List<Sociotabela> TodosOsSociosView() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Sociotabela", Sociotabela.class)
                .getResultList();
    }

    public List<Socio> TodosOsSociosJDBC() {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from socio", Socio.class)
                .getResultList();

    }

    public List<EnderecoPessoa> TodosOsEnderecosPessoasJDBC(int idSocio) {
        return sessionFactory.getCurrentSession()
                .createQuery(""
                        + "select "
                        + "e.id, "
                        + "e.numero from  "
                        + "enderecopessoa e "
                        + "inner join pessoa p on e.idPessoa = p.id "
                        + "inner join socio s on s.idPessoa = p.id "
                        + "where s.id = :id", EnderecoPessoa.class)
                .setParameter("id", idSocio)
                .getResultList();
    }

    public Endereco fromEnderecoPessoaJDBC(int idEnderecoPessoa) {
        return sessionFactory.getCurrentSession()
                .createQuery("select e.nome, e.tipo from endereco e inner join enderecopessoa ep on e.id = ep.idEndereco where ep.id = :idEnderecoPessoa", Endereco.class)
                .setParameter("id", idEnderecoPessoa)
                .getSingleResult();
    }

    public Pessoa fromSocioJDBC(int idSocio) {
        return sessionFactory.getCurrentSession()
                .createQuery("select p.nome, p.sobrenome, p.cpf from pessoa p inner join socio s on s.idPessoa = p.id where s.id = :idSocio", Pessoa.class)
                .setParameter("idSocio", idSocio)
                .getSingleResult();
    }

    public CategoriaSocio fromCategoriaSocioJDBC(int idEnderecoPessoa) {
        return sessionFactory.getCurrentSession()
                .createQuery("select c.nome from categoriasocio c inner join enderecopessoa e on c.id = e.idCategoriaSocio inner join pessoa p on e.idpessoa = p.id inner join socio s on s.idpessoa = p.id where e.id = :idEnderecoPessoa", CategoriaSocio.class)
                .setParameter("idEnderecoPessoa", idEnderecoPessoa)
                .getSingleResult();
    }

    public List<EnderecoPessoa> fromSocioTodosOsMetodosJDBC(int idSocio) {
        return sessionFactory.getCurrentSession()
                .createQuery("select * from enderecopessoa e "
                        + "inner join pessoa p on p.id = e.idpessoa "
                        + "inner join socio s on s.idpessoa = p.id "
                        + "inner join categoriasocio cs on cs.id = s.idcategoriasocio "
                        + "where s.id = :idSocio", EnderecoPessoa.class)
                .setParameter("idSocio", idSocio)
                .getResultList();

    }
}
