package br.org.acal.application.legacy.ui.table.render;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render  extends DefaultTableCellRenderer {
        private final Color whiteColor = new Color(254, 254, 254);
        private final Color alternateColor = new Color(204, 204, 204);
        private final Color selectedColor = new Color(61, 128, 223);

        @Override
        public Component getTableCellRendererComponent
        (JTable table,  Object value, boolean selected, boolean focused, int row,  int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            Color bg;
            
            if (row%2==0)
                bg = (whiteColor);
            else
                bg = selectedColor;

            setBackground(bg);
            setForeground(selected ? Color.white : Color.black);
          
            return this;
        }
    }

    
