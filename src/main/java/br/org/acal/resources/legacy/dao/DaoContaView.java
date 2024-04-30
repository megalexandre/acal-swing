package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.CaixaView;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoContaView {
      
    private final SessionFactory sessionFactory;

    public DaoContaView(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public List<CaixaView> BuscarTodasContas(){
        String hql = "from CaixaView";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .getResultList();
    }

    public List<CaixaView> BuscarContasAtrasadas60(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -60);
        String hql = "from CaixaView where vencimento < :para";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("para", c)
                .getResultList();
    }

     public List<CaixaView> BuscarPagamento(Date ini, Date fim) {
        String hql = "from CaixaView where Pagamento between :ini and :fim";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("ini", ini)
                .setParameter("fim", fim)
                .getResultList();
    }
     
     
    public List<CaixaView> BuscarVencimento(Date ini, Date fim) {
        String hql = "from CaixaView where vencimento between :ini and :fim";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CaixaView.class)
                .setParameter("ini", ini)
                .setParameter("fim", fim)
                .getResultList();
    }
}