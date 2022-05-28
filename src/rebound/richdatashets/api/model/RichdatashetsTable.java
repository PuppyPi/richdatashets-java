package rebound.richdatashets.api.model;

import static java.util.Arrays.*;
import static java.util.Objects.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import rebound.richshets.model.cell.RichshetCellContents;

/**
 * Note: while row indexes match up perfectly with the underlying thing if there is such a thing (after the frozen header rows), column indexes certainly don't generally!!<br>
 * They're only useful here in this object!<br>
 * (I mean there are two different kinds that both start at zero so that could never be XD )<br>
 * 
 * + The {@link RichshetCellContents}s in single-value cells and inside multivalue cell {@link List}s are {@link Nonnull not nullable}.  The lists are however able to be empty.
 */
public class RichdatashetsTable
{
	protected final RichdatashetsSemanticColumns columnsSingleValued;
	protected final RichdatashetsSemanticColumns columnsMultiValued;
	
	protected List<RichdatashetsRow> rows;
	
	
	public RichdatashetsTable(List<String> columnUIDsSingleValued, List<String> columnUIDsMultiValued)
	{
		this(new RichdatashetsSemanticColumns(columnUIDsSingleValued), new RichdatashetsSemanticColumns(columnUIDsMultiValued));
	}
	
	public RichdatashetsTable(RichdatashetsSemanticColumns columnsSingleValued, RichdatashetsSemanticColumns columnsMultiValued)
	{
		this(columnsSingleValued, columnsMultiValued, new ArrayList<>());
	}
	
	/**
	 * + Note that there can't be any overlap between the UIDs of single and multi value column-sets!
	 * @param rows  this will be kept as a live reference!  (you can set it to null briefly, but make sure to {@link #setRows(List) set it} to something sensible before it's used!!)
	 */
	public RichdatashetsTable(@Nonnull RichdatashetsSemanticColumns columnsSingleValued, @Nonnull RichdatashetsSemanticColumns columnsMultiValued, List<RichdatashetsRow> rows)
	{
		requireNonNull(columnsSingleValued);
		requireNonNull(columnsMultiValued);
		
		Set<String> uidOverlap = columnsSingleValued.getUIDOverlapWith(columnsMultiValued);
		if (!uidOverlap.isEmpty())
			throw new IllegalArgumentException("Overlap between single and multi valued columns' UIDs!!: "+uidOverlap);
		
		this.columnsSingleValued = columnsSingleValued;
		this.columnsMultiValued = columnsMultiValued;
		this.rows = rows;
	}
	
	
	
	
	
	
	public RichdatashetsSemanticColumns getColumnsSingleValued()
	{
		return columnsSingleValued;
	}
	
	public RichdatashetsSemanticColumns getColumnsMultiValued()
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
	 * @return it's just a plain list of {@link RichdatashetsRow}s and nulls, fully writable and {@link #setRows(List) changeable}!
	 */
	public List<RichdatashetsRow> getRows()
	{
		return rows;
	}
	
	/**
	 * @param rows  (a live reference will be kept to it!)
	 */
	public void setRows(List<RichdatashetsRow> rows)
	{
		this.rows = rows;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public @Nonnull RichshetCellContents getCell(int columnIndex, int rowIndex) throws IndexOutOfBoundsException
	{
		return rows.get(rowIndex).getSingleValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public void setCell(int columnIndex, int rowIndex, @Nonnull RichshetCellContents value) throws IndexOutOfBoundsException
	{
		requireNonNull(value);
		rows.get(rowIndex).getSingleValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public @Nonnull RichshetCellContents getCell(String columnUID, int rowIndex) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		return getCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public void setCell(String columnUID, int rowIndex, @Nonnull RichshetCellContents value) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		setCell(columnsSingleValued.requireIndexByUID(columnUID), rowIndex, value);
	}
	
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetsRow}s ofc!  (eg, with {@link #setMultiCell(int, int, List)})
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public @Nonnull List<RichshetCellContents> getMultiCell(int columnIndex, int rowIndex) throws IndexOutOfBoundsException
	{
		return rows.get(rowIndex).getMultiValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @param rowIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or rowIndex is too small or large
	 */
	public void setMultiCell(int columnIndex, int rowIndex, @Nonnull List<RichshetCellContents> value) throws IndexOutOfBoundsException
	{
		rows.get(rowIndex).getMultiValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetsRow}s ofc!  (eg, with {@link #setMultiCell(String, int, List)})
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public @Nonnull List<RichshetCellContents> getMultiCell(String columnUID, int rowIndex) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		return getMultiCell(columnsMultiValued.requireIndexByUID(columnUID), rowIndex);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @param rowIndex  starts at 0
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if rowIndex is too small or large
	 */
	public void setMultiCell(String columnUID, int rowIndex, List<RichshetCellContents> value) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		setMultiCell(columnsMultiValued.requireIndexByUID(columnUID), rowIndex, value);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @param columnIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or row is too small or large
	 */
	public @Nonnull RichshetCellContents getCell(int columnIndex, RichdatashetsRow row) throws IndexOutOfBoundsException
	{
		return row.getSingleValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or row is too small or large
	 */
	public void setCell(int columnIndex, RichdatashetsRow row, @Nonnull RichshetCellContents value) throws IndexOutOfBoundsException
	{
		requireNonNull(value);
		row.getSingleValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if row is too small or large
	 */
	public @Nonnull RichshetCellContents getCell(String columnUID, RichdatashetsRow row) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		return getCell(columnsMultiValued.requireIndexByUID(columnUID), row);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if row is too small or large
	 */
	public void setCell(String columnUID, RichdatashetsRow row, @Nonnull RichshetCellContents value) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		setCell(columnsMultiValued.requireIndexByUID(columnUID), row, value);
	}
	
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetsRow}s ofc!  (eg, with {@link #setMultiCell(int, int, List)})
	 * @param columnIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or row is too small or large
	 */
	public @Nonnull List<RichshetCellContents> getMultiCell(int columnIndex, RichdatashetsRow row) throws IndexOutOfBoundsException
	{
		return row.getMultiValuedColumns().get(columnIndex);
	}
	
	/**
	 * @param columnIndex  starts at 0
	 * @throws IndexOutOfBoundsException  if columnIndex or row is too small or large
	 */
	public void setMultiCell(int columnIndex, RichdatashetsRow row, @Nonnull List<RichshetCellContents> value) throws IndexOutOfBoundsException
	{
		row.getMultiValuedColumns().set(columnIndex, value);
	}
	
	
	/**
	 * The lists of multivalue "cells" are generally writable and mutable (eg, {@link ArrayList}s not {@link Arrays#asList(Object...)}s)
	 * But it's up to you if you put fixed-length or otherwise readonly implementations in the {@link RichdatashetsRow}s ofc!  (eg, with {@link #setMultiCell(String, int, List)})
	 * @param columnUID  case insensitive (auto uppercased)
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if row is too small or large
	 */
	public @Nonnull List<RichshetCellContents> getMultiCell(String columnUID, RichdatashetsRow row) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		return getMultiCell(columnsMultiValued.requireIndexByUID(columnUID), row);
	}
	
	/**
	 * @param columnUID  case insensitive (auto uppercased)
	 * @throws RichdatashetsNoSuchColumnException  if there is no single-valued column by that uid
	 * @throws IndexOutOfBoundsException  if row is too small or large
	 */
	public void setMultiCell(String columnUID, RichdatashetsRow row, List<RichshetCellContents> value) throws RichdatashetsNoSuchColumnException, IndexOutOfBoundsException
	{
		setMultiCell(columnsMultiValued.requireIndexByUID(columnUID), row, value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Adds a new row with {@link RichshetCellContents#Blank blank} cells to the end, returning it in case you want to edit it!<br>
	 * (Its index will be = {@link #getNumberOfRows()} just before this is called :3 )<br>
	 * <br>
	 * The lists in multivalued columns will be writable but (initially) empty lists.<br>
	 */
	public RichdatashetsRow addRow()
	{
		return addRow(RichshetCellContents.Blank);
	}
	
	/**
	 * Like {@link #addRow()} but you get to set the value of newly-created cells (single-valued ones; multi-valued ones still start with each their own separate empty mutable list)
	 */
	public RichdatashetsRow addRow(RichshetCellContents newSingleValuedCellValues)
	{
		RichdatashetsRow row = new RichdatashetsRow();
		
		RichshetCellContents[] s = new RichshetCellContents[getNumberOfColumnsSingleValued()];
		List<RichshetCellContents>[] m = new List[getNumberOfColumnsMultiValued()];
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
	public RichdatashetsRow removeRow(int index) throws IndexOutOfBoundsException
	{
		return getRows().remove(index);
	}
}
