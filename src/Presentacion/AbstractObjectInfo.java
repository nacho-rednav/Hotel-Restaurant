package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public abstract class AbstractObjectInfo<T> extends AbstractTableModel{
	protected String[] fields;
	protected List<T> data;
	
	public AbstractObjectInfo() {
		data = new ArrayList<T>();
		fireTableDataChanged();
	}
	
	public void setData(Collection<T> data){
		this.data = new ArrayList<T>(data);
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return fields[col];
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return fields.length;
	}
	
	public JPanel toPanel(String title){
		JTable c = new JTable(this);
		JPanel p = new JPanel(new BorderLayout());

		p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), title));
		p.add(new JScrollPane(c));
		return p;
	}
}
