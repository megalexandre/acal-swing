package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Conta;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoContasMensais implements ContasInterface {

    private final SessionFactory sessionFactory;
    
    public DaoContasMensais(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void AdicionarConta(Conta conta) {
        sessionFactory.getCurrentSession().persist(conta);
    }

    public Boolean isExisteContaAnteriorAberta(){
        return true;
    }
    
    public Boolean isExisteContaSQL(String sql){
        return sessionFactory.getCurrentSession()
            .createQuery(sql, List.class)
            .getResultList().isEmpty();
    }
    
    @Override
    public void ApagarConta(Conta conta) {
        sessionFactory.getCurrentSession().remove(conta);
    }

    @Override
    public void AtualizarConta(Conta conta) {
        sessionFactory.getCurrentSession().persist(conta);
    }
    
    public List<Date> datasContas(String number){    
        String hql = ""
                + "select c.dataVence "
                + "from Conta c "
                + "inner join enderecopessoa e on c.idenderecopessoa = e.id "
                + "where e.numero =:numero";
        return sessionFactory.getCurrentSession()
                 .createQuery(hql, Date.class)
                 .setParameter("numero", number)
                 .getResultList();
        
    }

    @Override
    public Conta ContasPorId(int id) {
        String hql = " from Conta where id =:id ";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Conta> ContasAbertas(Date inicio, Date fim) {
        String hql = " from Conta where datapagamento is null and datavencimento between :ini and :fim ";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("ini", inicio)
                .setParameter("fim", fim)
                .getResultList();
    }

    @Override
    public List<Conta> ContasAbertasCliente(int id) {
        String hql = "from Conta where datapagamento is null and idSocio =:id";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Conta> ContasAbertasCliente(String nome) {
        String hql = "from Conta where datapagamento is null";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .getResultList();
    }

    @Override
    public List<Conta> ContasVencidas(Date date) {
        String hql = "from Conta where dataPag is null and dataVence < :date";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Conta> ContasVencidasPorCliente(Date date, int id) {
        String hql = "from Contas where dataPag is null and dataVence < :data and idSocio = :id";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("date", date)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Conta> ContasVencidasPorCliente(Date data, String nome) {
        String hql = "from Contas where dataPag is null and dataVence < :data and Pesssoa.nome like :nome";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("data", data)
                .setParameter("nome","%" + nome + "%")
                .getResultList();
    }

    @Override
    public List<Conta> ContasTotalAbertas() {
        String hql = "from Conta where dataPag is null";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .getResultList();
    }

    @Override
    public List<Conta> ContaSomaPorData(Date inicio, Date fim) {
        String hql = "select sum(valor) from contas where datapag between :inicio and :fim";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("inicio",inicio)
                .setParameter("fim",fim)
                .getResultList();
    }

    @Override
    public List<Conta> ContasPorRua(int idRua) {
         String hql = "from Conta where endereco =:idRua";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("idRua",idRua)
                .getResultList();
    }

    @Override
    public List<Conta> ContasAbertas(Date data) {
        String hql = "from Conta where datapag is null and datavencimento > data";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Conta.class)
                .setParameter("data",data)
                .getResultList();
    }
}
