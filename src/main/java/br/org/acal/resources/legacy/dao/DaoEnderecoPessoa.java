package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.EnderecoPessoa;
import br.org.acal.resources.legacy.entidades.Geracaocontas;
import br.org.acal.resources.legacy.entidades.Pessoa;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoEnderecoPessoa {

    private final SessionFactory sessionFactory;

    public DaoEnderecoPessoa(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public EnderecoPessoa EnderecopessoaporNumero(String numero) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Enderecopessoa t where t.numero = :numero", EnderecoPessoa.class)
                .setParameter("numero", numero)
                .getSingleResult();
    }

    public boolean BuscaCnpj(String cnpj) {
        return Optional.of(
                sessionFactory.getCurrentSession()
                        .createQuery("from Pessoa p where p.cnpj = :cnpj", Pessoa.class)
                        .setParameter("cnpj", cnpj)
                        .getSingleResult()).isPresent();
    }

    public boolean getSocioExclusivo(String numero) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Enderecopessoa p where p.socioExclusivo= :numero", EnderecoPessoa.class)
                .setParameter("numero", numero)
                .getSingleResult().getSocioExclusivo();
    }

    public boolean numeroDisponivel(String numero) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Enderecopessoa p where p.numero = :numero", EnderecoPessoa.class)
                .setParameter("numero", numero)
                .getResultList().size() <= 0;
    }

    public String donoDoNumero(String numero) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Enderecopessoa ep inner join ep.idPessoa where ep.numero = :numero", EnderecoPessoa.class)
                .setParameter("numero", numero)
                .getSingleResult().getIdPessoa().getNome();
    }

    public int qtdRegistros() {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from enderecopessoa", int.class)
                .getSingleResult();
    }

    public List<Geracaocontas> TodosOsSocios() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Geracaocontas", Geracaocontas.class)
                .getResultList();
    }

    public List<EnderecoPessoa> TodosOsSocios(int inicio, int total) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Enderecopessoa", EnderecoPessoa.class)
                .getResultList();
    }

    public void ApagarEnderecopessoa(EnderecoPessoa p) {
        sessionFactory.getCurrentSession().remove(p);
    }

    public void AtualizarEndereco(EnderecoPessoa endereco, int op) {
        sessionFactory.getCurrentSession().persist(endereco);
    }

}
