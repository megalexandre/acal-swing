package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Pessoa;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoPessoa implements PessoasInterface {

     private final SessionFactory sessionFactory;
    
    public DaoPessoa(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void AdicionarPessoa(Pessoa pessoa) {
        sessionFactory.getCurrentSession().persist(pessoa);
    }

    @Override
    public void ApagarPessoa(Pessoa pessoa) {
        sessionFactory.getCurrentSession().remove(pessoa);
    }

    @Override
    public void AlterarPessoa(Pessoa pessoa) {
        sessionFactory.getCurrentSession().persist(pessoa);
    }
    
    public boolean getSocioExclusivo(String nomeCompleto){
          return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa p where lower(concat(p.nome,' ', p.sobrenome)) = lower(:nomeCompleto)", Pessoa.class)
                .setParameter("nomeCompleto", nomeCompleto)
                .getSingleResult().getSocio().getSocioExclusivo();
    }
    
    
    @Override
    public Pessoa BuscarNomeCompleto(String nomeCompleto){
         return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa p where lower(concat(p.nome,' ', p.sobrenome)) = lower(:nomeCompleto)", Pessoa.class)
                .setParameter("nomeCompleto", nomeCompleto)
                .getSingleResult();
    }  
    
    @Override
    public List<Pessoa> BuscarPessoaLikeNomeCompleto(String nomeCompleto) {
         return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa p where lower(concat(p.nome,' ', p.sobrenome)) LIKE lower(:nomeCompleto)", Pessoa.class)
                .setParameter("nomeCompleto", nomeCompleto)
                .getResultList();
    }
    
    @Override
    public List<Pessoa> BuscarPessoaLikeNome(String nome){
        return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa p where lower(concat(p.nome,' ', p.sobrenome)) LIKE :nome", Pessoa.class)
                .setParameter("nome", "%"+nome.toLowerCase()+"%")
                .getResultList();
    }
    
    @Override
    public List<Pessoa> BuscarPessoaLikeSobrenome(String nome){
        return BuscarPessoaLikeNome(nome);
    }
 
    
    @Override
    public Pessoa BuscarPessoaId(int id) {
       return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa where id = :id", Pessoa.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Pessoa BuscarPessoaNome(String nome) {
         return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa where nome = :nome", Pessoa.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    @Override
    public Pessoa BuscarPessoaCpf(String cpf) {
       return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa where cpf = :cpf", Pessoa.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    @Override
    public List<Pessoa> BuscarTodos() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pessoa", Pessoa.class)
                .getResultList();
    }

}