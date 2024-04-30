package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Motivodespesa;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoMotivoDespesa implements MotivoDespesasInterface {

    private final SessionFactory sessionFactory;

    public DaoMotivoDespesa(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AdicionarMotivoDespesa(Motivodespesa motivo) {
        throw new RuntimeException();
    }

    @Override
    public void ApagarMotivoDespesa(Motivodespesa motivo) {
        throw new RuntimeException();

    }

    @Override
    public void AlterarMotivoDespesa(Motivodespesa motivo) {
        throw new RuntimeException();

    }

    @Override
    public List<Motivodespesa> BuscarMotivoDespesaLikeNome(String nome) {
        throw new RuntimeException();

    }

    @Override
    public Motivodespesa BuscarMotivoDespesaId(int id) {
        throw new RuntimeException();

    }

    @Override
    public List<Motivodespesa> BuscarMotivo(String nome) {
        throw new RuntimeException();

    }

    @Override
    public List<Motivodespesa> BuscarTodosMotivos() {
        throw new RuntimeException();

    }

}
