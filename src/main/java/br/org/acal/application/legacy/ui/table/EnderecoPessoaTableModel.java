package br.org.acal.application.legacy.ui.table;

import br.org.acal.resources.legacy.entidades.EnderecoPessoa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class EnderecoPessoaTableModel extends AbstractTableModel {

    private static final int numero = 0;
    private static final int logradouro = 1;
    private static final int matricula = 2;
    private static final int inativo = 3;
    private static final int exclusivo = 4;
    private static final int categoriaSocio = 5;

    private final List<EnderecoPessoa> linhas;

    public EnderecoPessoaTableModel() {
        linhas = new ArrayList<>();
    }

    public EnderecoPessoaTableModel(List<EnderecoPessoa> listaDoCaixa) {
        linhas = new ArrayList<>(listaDoCaixa);
    }

    private final String[] colunas = new String[]{
        "Numero",
        "Logradouro",
        "Matricula",
        "Inativo",
        "Exclusivo",
        "Categoria Socio"

    };

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    ;
  
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case numero -> {
                return String.class;
            }
            case logradouro, categoriaSocio -> {
                return JComboBox.class;
            }
            case matricula -> {
                return Date.class;
            }
            case inativo, exclusivo -> {
                return Boolean.class;
            }

            default -> throw new IndexOutOfBoundsException("Index Não Encontrado");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        EnderecoPessoa c = linhas.get(rowIndex);

        switch (columnIndex) {
            case numero -> {
                return c.getNumero();
            }
            case logradouro -> {
                return c.getIdEndereco().getTipo() + " " + c.getIdEndereco().getNome();
            }
            case matricula -> {
                return c.getDatamatricula();
            }
            case inativo -> {
                return c.getInativo();
            }
            case exclusivo -> {
                return c.getSocioExclusivo();
            }
            case categoriaSocio -> {
                return c.getIdCategoriaSocio().getNome();
            }
            default -> throw new IndexOutOfBoundsException("Campo Não Encontrado");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        EnderecoPessoa c = linhas.get(rowIndex);

        switch (columnIndex) {
            case numero -> c.setNumero((String) aValue);
            case logradouro -> c.getIdEndereco().setNome((String) aValue);
            case matricula -> c.setDatamatricula((Date) aValue);
            case inativo -> c.setInativo((Boolean) aValue);
            case exclusivo -> c.setSocioExclusivo((Boolean) aValue);
            case categoriaSocio -> c.getIdCategoriaSocio().setNome((String) aValue);
            default -> throw new IndexOutOfBoundsException("Alteração de ID Não permintida ou Index inválido");

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public EnderecoPessoa getLinha(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addLinha(EnderecoPessoa caixa) {
        linhas.add(caixa);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeLinha(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeCaixa(List<EnderecoPessoa> caixaLinha) {
        int indice = getRowCount();
        linhas.addAll(caixaLinha);

        fireTableRowsInserted(indice, indice + caixaLinha.size());
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

}
