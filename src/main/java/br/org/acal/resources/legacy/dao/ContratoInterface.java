/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Contrato;
import java.util.List;

/**
 *
 * @author alexandre
 */
public interface ContratoInterface {
    
    public void AdcionarContrato  (Contrato contrato);
    public void ApagarCategoria   (Contrato contrato);
    public void AtualizarCategoria(Contrato contrato);
    
    public List<Contrato> BuscarContrato(int id);
    
}
