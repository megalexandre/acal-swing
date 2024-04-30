/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.CategoriaSocio;

import java.util.List;


/**
 *
 * @author Alexandre
 */
public interface CategoriaSocioInterface {
    
    public void AdicionarCategoria(CategoriaSocio categoria);
    public void ApagarCategoria   (CategoriaSocio categoria);
    public void AtualizarCategoria(CategoriaSocio categoria);
    
    public CategoriaSocio       BuscarCategoriaPorNome(String nome);
    public List<CategoriaSocio> BuscarCategoriaPorNomeLike (String nome);
    public CategoriaSocio       BuscarCategoriaSocioPorId(int id);
    public List<CategoriaSocio> BuscarTodasCategorias();
}
