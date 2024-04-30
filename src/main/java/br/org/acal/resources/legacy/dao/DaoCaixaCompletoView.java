package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.RcCaixaCompleto;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCaixaCompletoView {
    
    private final SessionFactory sessionFactory;

    public DaoCaixaCompletoView(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

     public List<RcCaixaCompleto> BuscarTodosRcCaixaCompleto() {
        return sessionFactory.getCurrentSession()
                .createQuery("from RcCaixaCompleto", RcCaixaCompleto.class)
                .getResultList();
    }
    
    public List<RcCaixaCompleto> BuscarTodosRcCaixaCompletoDatePagamento(Date ini, Date fim) {
        return sessionFactory.getCurrentSession()
                .createQuery("from RcCaixaCompleto where Pagamento between :ini and :fim", RcCaixaCompleto.class)
                .setParameter("ini", ini)
                .setParameter("fim", fim)
                .getResultList();
    }
    
    
    public List<RcCaixaCompleto> BuscarTodosRcCaixaCompletoDateVencimento(Date ini, Date fim) {
        return sessionFactory.getCurrentSession()
                .createQuery("from RcCaixaCompleto where vencimento between :ini and :fim", RcCaixaCompleto.class)
                .setParameter("ini", ini)
                .setParameter("fim", fim)
                .getResultList();
    }
}

