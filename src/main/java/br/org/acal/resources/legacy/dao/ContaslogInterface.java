/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.legacy.entidades.Contaslog;

import java.util.List;

/**
 *
 * @author alexandre
 */
public interface ContaslogInterface {
  
    public Contaslog ListarPorId  (int id);
    public List<Contaslog> ListarPorTipo(String tipo);
    public List<Contaslog> ListarTodas  ();
}
