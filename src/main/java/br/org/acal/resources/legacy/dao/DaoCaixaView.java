package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.CaixaView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class DaoCaixaView {

    private final SessionFactory sessionFactory;

    public DaoCaixaView(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CaixaView> BuscarTodosCaixaView() {
        String hql = "from CaixaView";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .getResultList();
    }

    public List<CaixaView> BuscarTodosCaixaViewDatePagamento(Date ini, Date fim) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String hql = "rom CaixaView where Pagamento between :ini and :fim";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("ini", sdf.format(ini))
                .setParameter("fim", sdf.format(fim))
                .getResultList();
    }

    public List<CaixaView> BuscarTodosCaixaViewDateVencimento(Date ini, Date fim) {
        String hql = "from CaixaView where vencimento between :ini and :fim";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("ini", ini)
                .setParameter("fim", fim)
                .getResultList();
    }

    public List<CaixaView> BuscarSocioNome(String socio) {
        String hql = "from CaixaView where socio = :socio";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("socio", socio)
                .getResultList();
    }

    public List<CaixaView> BuscarLogradouro(String endereco) {
        String hql = "from CaixaView where endereco = :endereco";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("endereco", endereco)
                .getResultList();
    }

    public List<CaixaView> BuscarStatus(int status) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<CaixaView> query;

        query = switch (status) {
            case 1 -> currentSession.createQuery("from CaixaView where pagamento is null", CaixaView.class);
            case 2 -> currentSession.createQuery("from CaixaView where pagamento is not null", CaixaView.class);
            default -> currentSession.createQuery("from CaixaView where vencimento <= :data and pagamento is null", CaixaView.class).setParameter("data", new Date());
        };

        return query.getResultList();
    }

    public List<CaixaView> BuscarSocioLogradouro(String endereco, String socio) {
        String hql = "from CaixaView where endereco = :endereco and socio = :socio";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("endereco", endereco)
                .setParameter("socio", socio)
                .getResultList();
    }

    public List<CaixaView> BuscarSocioStatus(int status, String socio) {
        String hql;
        
           switch (status) {
               case 1 -> hql = "from CaixaView where socio = :socio and pagamento is null";
               case 2 -> hql = "from CaixaView where socio = :socio and pagamento is not null";
               case 3 -> hql = "from CaixaView where socio = :socio and vencimento <= :data and pagamento is null";
               default -> throw new IllegalArgumentException("Status inválido: " + status);
           }

           return sessionFactory.getCurrentSession()
                   .createQuery(hql, CaixaView.class)
                   .setParameter("socio", socio)
                   .setParameter("data", new Date())
                   .getResultList();
    }

    public List<CaixaView> BuscarLogradouroStatus(int status, String end) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<CaixaView> query;
        
        query = switch (status) {
            case 1 -> currentSession.createQuery("from CaixaView where pagamento is null and endereco = :endereco", CaixaView.class);
            case 2 -> currentSession.createQuery("from CaixaView where pagamento is not null and endereco = :endereco", CaixaView.class);
            case 3 -> currentSession
                .createQuery("from CaixaView where vencimento <= :data and pagamento is null and endereco = :endereco", CaixaView.class)
                .setParameter("data", new Date());
            default -> throw new IllegalArgumentException("Status inválido: " + status);
        };
        
        return query
                .setParameter("end", end)
                .getResultList();
    }

    public List<CaixaView> BuscarTodosCriterios(int status, String socio, String endereco) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<CaixaView> query;
        
        query = switch (status) {
            case 1 -> currentSession.createQuery("from CaixaView where pagamento is null and socio = :socio and endereco = :endereco", CaixaView.class);
            case 2 -> currentSession.createQuery("from CaixaView where pagamento is not null and socio = :socio and endereco = :endereco", CaixaView.class);
            case 3 -> currentSession
                .createQuery("from CaixaView where vencimento <= :data and pagamento is null and socio = :socio and endereco = :endereco", CaixaView.class)
                .setParameter("data", new Date());
            default -> throw new IllegalArgumentException("Status inválido: " + status);
        };
        
        return query
                .setParameter("endereco", endereco)
                .setParameter("socio", socio)
                .getResultList();
    }
}
