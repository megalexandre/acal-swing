package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Motivoentrada;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DaoMotivoEntrada implements MotivoEntradasInterface {

    @Override
    public void AdicionarMotivoEntrada(Motivoentrada motivo) {
        throw new RuntimeException();
    }

    @Override
    public void ApagarMotivoEntrada(Motivoentrada motivo) {
        throw new RuntimeException();
    }

    @Override
    public Motivoentrada BuscarMotivoEntradaId(int id) {
        throw new RuntimeException();
    }

    @Override
    public List<Motivoentrada> BuscarMotivoEntradaLikeNome(String nome) {
        throw new RuntimeException();
    }

    @Override
    public void AlterarMotivoEntrada(Motivoentrada motivo) {
        throw new RuntimeException();

    }

    @Override
    public List<Motivoentrada> BuscarMotivo(String nome) {
        throw new RuntimeException();
    }

    @Override
    public List<Motivoentrada> BuscarTodosMotivos() {
        throw new RuntimeException();
    }

}
