package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Taxa;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoTaxa implements TaxasInterface {

    private final SessionFactory sessionFactory;

    public DaoTaxa(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void AdicionarTaxa(Taxa taxa) {
        sessionFactory.getCurrentSession().persist(taxa);
    }

    @Override
    public void ApagarTaxa(Taxa taxa) {
        sessionFactory.getCurrentSession().remove(taxa);
    }

    @Override
    public void AlterarTaxa(Taxa taxa) {
        sessionFactory.getCurrentSession().persist(taxa);
    }

    @Override
    public Taxa TaxaPorId(int id) {
      return sessionFactory.getCurrentSession()
              .createQuery("from Taxa t where t.id = :id", Taxa.class)
              .getSingleResult();
    }
    

    @Override
    public Taxa TaxasPorNome(String nome) {
        return sessionFactory.getCurrentSession()
              .createQuery("from Taxa t where t.nome = :nome", Taxa.class)
              .setParameter(nome, nome)
              .getSingleResult();
    }

    @Override
    public List<Taxa> BuscarTaxaNomeLike(String nome) {
        String hql = "from Taxa e where e.nome LIKE :nome";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Taxa.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }
        
    @Override
    public List<Taxa> TaxasTodas() {
       return sessionFactory.getCurrentSession()
              .createQuery("from Taxa", Taxa.class)
              .getResultList();
    }
   
}
