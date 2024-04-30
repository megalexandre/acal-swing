package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.CategoriaSocio;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoCategoriaSocio implements CategoriaSocioInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void AdicionarCategoria(CategoriaSocio categoria) {
        sessionFactory.getCurrentSession().persist(categoria);
    }

    @Override
    public void ApagarCategoria(CategoriaSocio categoria) {
        sessionFactory.getCurrentSession().remove(categoria);
    }

    @Override
    public void AtualizarCategoria(CategoriaSocio categoria) {
        sessionFactory.getCurrentSession().persist(categoria);
    }

    @Override
    public CategoriaSocio BuscarCategoriaPorNome(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Categoriasocio where nome = :name", CategoriaSocio.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<CategoriaSocio> BuscarCategoriaPorNomeLike(String nome) {
        String hql = "FROM CategoriaSocio WHERE nome = :nome";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CategoriaSocio.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    @Override
    public List<CategoriaSocio> BuscarTodasCategorias() {
        String hql = "from Categoriasocio order by nome";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CategoriaSocio.class)
                .getResultList();
    }

    @Override
    public CategoriaSocio BuscarCategoriaSocioPorId(int id) {
        String hql = "from Categoriasocio where id = :id";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, CategoriaSocio.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
