package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Endereco;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoEndereco implements EnderecosInterface {

    private final SessionFactory sessionFactory;

    public DaoEndereco(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AdicionarEndereco(Endereco endereco) {
        sessionFactory.getCurrentSession().persist(endereco);
    }

    @Override
    public void ApagarEndereco(Endereco endereco) {
         sessionFactory.getCurrentSession().remove(endereco);
    }

    @Override
    public void AlterarEndereco(Endereco endereco) {
        sessionFactory.getCurrentSession().persist(endereco);
    }

    @Override
    public List<Endereco> BuscarEnderecoNomeLike(String nome) {
        return sessionFactory.getCurrentSession()
            .createQuery("from Endereco e where e.nome LIKE :nome", Endereco.class)
            .setParameter("nome", "%" + nome + "%")
            .getResultList();    
    }

    public Endereco BuscarEnderecoCompleto(String nome) {
        return sessionFactory.getCurrentSession()
            .createQuery("from Endereco p where lower(concat(p.tipo,' ', p.nome)) = lower(:nome)", Endereco.class)
            .setParameter("nome", "%" + nome + "%")
            .getSingleResult();  
    }

    @Override
    public List<Endereco> BuscarTodosEnderecos() {
        return sessionFactory.getCurrentSession()
            .createQuery("from Endereco order by tipo,nome ", Endereco.class)
            .getResultList();
    }

    @Override
    public Endereco BuscarPorId(int id) {
        return sessionFactory.getCurrentSession()
            .createQuery("from Endereco e where e.id = :id", Endereco.class)
            .setParameter("id", id)
            .getSingleResult();
    }

}
