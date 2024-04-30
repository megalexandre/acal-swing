package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Saida;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoSaidas implements SaidasInterface {

    private final SessionFactory sessionFactory;

    public DaoSaidas(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AdicionarSaida(Saida saida) {
        throw new RuntimeException();

    }

    @Override
    public void AlterarSaida(Saida saida) {
        throw new RuntimeException();

    }

    @Override
    public void ApagarSaida(Saida saida) {
        throw new RuntimeException();

    }

    @Override
    public Saida BuscarSaidaId(int id) {
        throw new RuntimeException();

    }

    @Override
    public List<Saida> BuscarSaidaFavorecidoLikeNome(String nome) {
        throw new RuntimeException();
    }

    @Override
    public BigDecimal SomaSaidas(Date inicio, Date fim) {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> BuscarTodasSaidas() {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> SaidasPorFuncionario(int IdFuncionario) {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> SaidasPorData(Date inicio, Date fim) {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> SaidaPorDataPorFuncionario(int idFuncionario, Date inicio, Date fim) {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> SaidaPorFuncionarioPorMotivo(int Motivo, int IdFuncionario) {
        throw new RuntimeException();
    }

    @Override
    public List<Saida> SaidasPorMotivo(int IdMotivoSaidas) {
        throw new RuntimeException();

    }

}
