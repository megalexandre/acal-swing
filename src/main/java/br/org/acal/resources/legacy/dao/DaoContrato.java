package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Contrato;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoContrato implements ContratoInterface {

    private final SessionFactory sessionFactory;

    public DaoContrato(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AdcionarContrato(Contrato contrato) {
        sessionFactory.getCurrentSession().persist(contrato);
    }

    @Override
    public void ApagarCategoria(Contrato contrato) {
        sessionFactory.getCurrentSession().remove(contrato);
    }

    @Override
    public void AtualizarCategoria(Contrato contrato) {
        sessionFactory.getCurrentSession().persist(contrato);
    }

    @Override
    public List<Contrato> BuscarContrato(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Contrato c where c.id :id", Contrato.class)
                .setParameter("id", id)
                .getResultList();
    }

}
