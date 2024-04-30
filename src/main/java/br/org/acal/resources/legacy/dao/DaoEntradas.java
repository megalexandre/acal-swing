package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Entrada;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoEntradas implements EntradasInterface {

    private final SessionFactory sessionFactory;

    public DaoEntradas(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AdicionarEntrada(Entrada entrada) {
        sessionFactory.getCurrentSession().persist(entrada);
    }

    @Override
    public void ApagarEntrada(Entrada entrada) {
        sessionFactory.getCurrentSession().remove(entrada);
    }

    public List<Entrada> BuscarEntradaCedenteLikeNome(String nome) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Entrada e where e.idCedente.idPessoa.nome LIKE :nome", Entrada.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public List<Entrada> BuscarTodasEntradas() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Entrada", Entrada.class)
                .getResultList();
    }

    @Override
    public void AlterarEntrada(Entrada entrada) {
        sessionFactory.getCurrentSession().persist(entrada);
    }

    @Override
    public BigDecimal SomarEntrada() {
        return sessionFactory.getCurrentSession()
                .createQuery("select sum(valor) from entrada where data between :inicio and :fim", BigDecimal.class)
                .getSingleResult();
    }

    @Override
    public BigDecimal SomarEntradaPorFuncionario(int idfuncionario) {
        return BigDecimal.ONE;
    }

    @Override
    public BigDecimal SomarEntradaPorData(Date inicio, Date fim) {
        return sessionFactory.getCurrentSession()
                .createQuery("select sum(valor) from entrada where data between :incio and :fim", BigDecimal.class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getSingleResult();
    }

    @Override
    public BigDecimal SomarEntradaPorFuncionarioPorData(int idfuncionario, Date inicio, Date fim) {
        return BigDecimal.ONE;

    }

    @Override
    public Entrada EntradaPorId(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Entrada where  id = :id", Entrada.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Entrada> EntradaPorFuncionario(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from entrada where idfuncionario = :id", Entrada.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Entrada> EntradaPorData(Date inicio, Date fim) {
        return sessionFactory.getCurrentSession()
                .createQuery("from entrada where  data between :incio and :fim", Entrada.class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getResultList();
    }

    @Override
    public List<Entrada> EntradaPorValor(int max, int min) {
        return sessionFactory.getCurrentSession()
                .createQuery("from entrada where  valor between :max and :min", Entrada.class)
                .setParameter("max", max)
                .setParameter("min", min)
                .getResultList();
    }

    @Override
    public List<Entrada> EntradaPorFuncionarioPorMotivo(int motivo, int idfuncionario) {
        throw new RuntimeException();
    }

    @Override
    public List<Entrada> EntradaPorMotivo(int idMotivo) {
        throw new RuntimeException();
    }

    @Override
    public List<Entrada> EntradraPorSocio(int idSocio) {
        throw new RuntimeException();
    }

}
