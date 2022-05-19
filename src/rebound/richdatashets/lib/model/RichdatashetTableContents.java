package rebound.richdatashets.lib.model;

import static java.util.Arrays.*;
import static java.util.Objects.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * Note: while row indexes match up perfectly with the Google Sheet (after the frozen header rows), column indexes certainly don't generally!!<br>
 * They're only useful here in this object!<br>
 * (I mean there are two different kinds that both start at zero so that could never be XD )<br>
 */
public class RichdatashetTableContents
{
	protected final RichdatashetSemanticColumns columnsSingleValued;
	protected final RichdatashetSemanticColumns columnsMultiValued;
	
	protected List<RichdatashetRow> rows;
	
	
	public RichdatashetTableContents(List<String> columnUIDsSingleValued, List<String> columnUIDsMultiValued)
	{
		this(new RichdatashetSemanticColumns(columnUIDsSingleValued), new RichdatashetSemanticColumns(columnUIDsMultiValued));
	}
	
	public RichdatashetTableContents(RichdatashetSemanticColumns columnsSingleValued, RichdatashetSemanticColumns columnsMultiValued)
	{
		this(columnsSingleValued, columnsMultiValued, new ArrayList<>());
	}
	
	/**
	 * @param rows  this will be kept as a live reference!
	 */
	public RichdatashetTableContents(RichdatashetSemanticColumns columnsSingleValued, RichdatashetSemanticColumns columnsMultiValued, List<RichdatashetRow> rows)
	{
		this.columnsSingleValued = columnsSingleValued;
		this.columnsMultiValued = columnsMultiValued;
		this.rows = rows;
	}
	
	
	
	
	
	
	public RichdatashetSemanticColumns getColumnsSingleValued()
	{
		return columnsSingleValued;
	}
	
	public RichdatashetSemanticColumns getColumnsMultiValued()
	{
		return columnsMultiValued;
	}
	
	
	public int getNumberOfColumnsSingleValued()
	{
		return columnsSingleValued.size();
	}
	
	public int getNumberOfColumnsMultiValued()
	{
		return columnsMultiValued.size();
	}
	
	
	public int getNumberOfRows()
	{
		return rows.size();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return it's just a plain list of {@link RichdatashetRow}s, fully writable and {@link #setRows(List) changeable}!
	 */
	public List<RichdatashetRow> getRows()
	{
		return rows;
	}
	
	public void setRows(List<RichdatashetRow> rows)
	{
		this.rows = rows;
	}
	
	
	
	
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public @Nonnull RichdatashetCell getCell(int columnIndex, int rowIndex) throws IndexOutOfBoundsException
	{
		return rows.get(rowIndex).getSingleValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public void setCell(int columnIndex, int rowIndex, @Nonnull RichdatashetCell value) throws IndexOutOfBoundsException
	{
		requireNonNull(value);
		rows.get(rowIndex).getSingleValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public @Nonnull RichdatashetCell getCell(String columnUID, int rowIndex) throws RichdatashetNoSuchColumnException, IndexOutOfBoundsException
	{
		return getCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public void setCell(String columnUID, int rowIndex, @Nonnull RichdatashetCell value) throws RichdatashetNoSuchColumnException, IndexOutOfBoundsException
	{
		setCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex, value);
	}
	
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetRow}s ofc!  (eg, with {@link #setMultiCell(int, int, List)})
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public @Nonnull List<RichdatashetCell> getMultiCell(int columnIndex, int rowIndex) throws IndexOutOfBoundsException
	{
		return rows.get(rowIndex).getMultiValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public void setMultiCell(int columnIndex, int rowIndex, @Nonnull List<RichdatashetCell> value) throws IndexOutOfBoundsException
	{
		rows.get(rowIndex).getMultiValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetRow}s ofc!  (eg, with {@link #setMultiCell(String, int, List)})
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public @Nonnull List<RichdatashetCell> getMultiCell(String columnUID, int rowIndex) throws RichdatashetNoSuchColumnException, IndexOutOfBoundsException
	{
		return getMultiCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public void setMultiCell(String columnUID, int rowIndex, List<RichdatashetCell> value) throws RichdatashetNoSuchColumnException, IndexOutOfBoundsException
	{
		setMultiCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex, value);
	}
	
	
	
	
	
	
	
	
	/**
	 * Adds a new blank row to the end, returning it if you want to edit it!<br>
	 * (Its index will be = {@link #getNumberOfRows()} just before this is called :3 )<br>
	 * <br>
	 * The lists in multivalued columns will be writable but (initially) empty lists.<br>
	 */
	public RichdatashetRow addBlankRow()
	{
		return addBlankRow(RichdatashetCell.Blank);
	}
	
	/**
	 * Like {@link #addBlankRow()} but you get to set the value of newly-created cells (single-valued ones; multi-valued ones still start with each their own separate empty mutable list)
	 */
	public RichdatashetRow addBlankRow(RichdatashetCell newSingleValuedCellValues)
	{
		RichdatashetRow row = new RichdatashetRow();
		
		RichdatashetCell[] s = new RichdatashetCell[getNumberOfColumnsSingleValued()];
		List<RichdatashetCell>[] m = new List[getNumberOfColumnsMultiValued()];
		Arrays.fill(s, newSingleValuedCellValues);
		
		for (int i = 0; i < m.length; i++)
			m[i] = new ArrayList<>();
		
		row.setSingleValuedColumns(asList(s));
		row.setMultiValuedColumns(asList(m));
		
		rows.add(row);
		
		return row;
	}
	
	
	
	
	/**
	 * Yes you can do this! :&gt;<br>
	 * The columns are quite immutable but you can add and remove rows however you like! :D
	 */
	public RichdatashetRow removeRow(int index) throws IndexOutOfBoundsException
	{
		return getRows().remove(index);
	}
}
