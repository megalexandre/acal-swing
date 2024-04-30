package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.EnderecoView;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoEnderecoView {
 
    private final SessionFactory sessionFactory;

    public DaoEnderecoView(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public List<EnderecoView> BuscarTodosEnderecos() {
        String hql = "from EnderecoView";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, EnderecoView.class)
                .getResultList();
    }
    
}
