/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.application.legacy.ui.table;

import br.org.acal.resources.legacy.entidades.CaixaView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexandre
 */
public class CaixaCompletoTableModel extends AbstractTableModel{
    
    private static final int numeroconta = 0;
    private static final int data        = 1;
    private static final int pagamento   = 2;
    private static final int vencimento  = 3;
    private static final int socio       = 4;
    private static final int endereco    = 5;
    private static final int numero      = 6;
    private static final int categoriaSocio = 7;
    private static final int taxaSocio   = 8;
    private static final int consumo     = 9;
    private static final int excessoValor = 10;
    private static final int totalconta = 11;
   
    private static final int data_set      = 1;
    private static final int vencimento_set= 2 ;
    private static final int pagamento_set = 3;
    private static final int consumo_set   = 4;
    
    private final List<CaixaView> linhas;
    
    public CaixaCompletoTableModel() {
        linhas = new ArrayList<>();
    }
    
    public CaixaCompletoTableModel(List<CaixaView> listaDoCaixa) {
        linhas = new ArrayList<>(listaDoCaixa);
    }
    
    private final String[] colunas = new String[] {
        "Conta",
        "Data", 
        "Pagamento", 
        "Vencimento",
        "Socio",
        "Endereco", 
        "Numero", 
        "CategoriaSocio", 
        "TaxaSocio", 
        "Consumo", 
        "ExcessoValor", 
        "Total"
    };
    
    @Override
    public int getRowCount(){
       return linhas.size();  
    }
    
    @Override
    public int getColumnCount() {
      return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    };
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case numeroconta:
            return Integer.class;
            case data:
            return Date.class;
            case pagamento:
            return Date.class;
            case vencimento:
            return Date.class;
            case socio:
            return String.class;
            case endereco:
            return String.class;
            case numero:
            return Integer.class;
            case categoriaSocio:
            return String.class;     
            case taxaSocio:
            return BigDecimal.class;  
            case consumo:
            return Integer.class;
            case excessoValor:
            return BigDecimal.class;
            case totalconta:
            return BigDecimal.class;
        default:
        throw new IndexOutOfBoundsException("Index Não Encontrado");
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    CaixaView c = linhas.get(rowIndex);
        switch (columnIndex) {
            case numeroconta -> {
                return c.getNumeroconta();
            }
            case data -> {
                return c.getDate();
            }
            case pagamento -> {
                return c.getPagamento();
            }
            case vencimento -> {
                return c.getVencimento();
            }
            case socio -> {
                return c.getSocio();
            }
            case endereco -> {
                return c.getEndereco();
            }
            case numero -> {
                return c.getNumero();
            }
            case categoriaSocio -> {
                return c.getCategoriaSocio();
            }
            case taxaSocio -> {
                return c.getTaxaSocio();
            }
            case consumo -> {
                return c.getConsumo();
            }
            case excessoValor -> {
                return c.getExcessoValor();
            }
            case totalconta -> {
                return c.getTotalconta();
            }
        default -> throw new IndexOutOfBoundsException("Campo Não Encontrado");
        }
    }
    
     public void setValueAt(CaixaView aValue, int rowIndex) {  
        CaixaView conta = linhas.get(rowIndex);
  
        conta.setDate(aValue.getDate());
        conta.setVencimento(aValue.getVencimento());
        conta.setPagamento(aValue.getPagamento());
        conta.setConsumo(aValue.getConsumo());
        
        fireTableCellUpdated(rowIndex, data_set);  
        fireTableCellUpdated(rowIndex, vencimento_set);  
        fireTableCellUpdated(rowIndex, pagamento_set);       
        fireTableCellUpdated(rowIndex, consumo_set); 
    }  
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       fireTableCellUpdated(rowIndex, columnIndex);
    }
   
    public CaixaView getLinha(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
 
    public void addLinha(CaixaView caixa) {
        linhas.add(caixa);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
 
    public void removeLinha(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
 
    public void addListaDeConta(List<CaixaView> caixaLinha) {
         int indice = getRowCount();
         linhas.addAll(caixaLinha);
         fireTableRowsInserted(indice, indice + caixaLinha.size());
    }
 
    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }   
}
