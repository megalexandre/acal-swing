package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.SociosView;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoSocioView {
    
    private final SessionFactory sessionFactory;

    public DaoSocioView(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public List<SociosView> BuscarTodosSociosView() {
        String hql = "from SociosView";
        return sessionFactory
                .getCurrentSession()
                .createQuery(hql, SociosView.class)
                .getResultList();
    }
}
