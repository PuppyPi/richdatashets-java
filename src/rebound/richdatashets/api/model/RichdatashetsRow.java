package rebound.richdatashets.api.model;

import java.util.List;
import rebound.richshets.model.cell.RichshetsCellContents;

/**
 * Note that, since some rows have extra cell data that's invisible to datashets client code (eg, because its column has no semantic uid; it's just for people not code), we must take care to preserve it!  Hence we have {@link #getOriginalDataRowIndex()}, the row index in the original
 * spreadsheet starting on the first data, non-header, row at 0.  This is used to copy data from the spreadsheet before overwriting it.  If this value here is -1, then it's treated as a new row that has been
 * added (though not necessarily to the end!  things can be inserted and rearranged however you like and if you take care of these original-indexes, then it won't mess up extra data/formatting :> )
 */
public class RichdatashetsRow
{
	protected List<RichshetsCellContents> singleValuedColumns;
	protected List<List<RichshetsCellContents>> multiValuedColumns;
	protected int originalDataRowIndex = -1;  //-1, 0, 1, 2, etc.
	
	public RichdatashetsRow()
	{
		super();
	}
	
	/**
	 * @param singleValuedColumns  live reference to it is kept!
	 * @param multiValuedColumns  live reference to it is kept!
	 */
	public RichdatashetsRow(List<RichshetsCellContents> singleValuedColumns, List<List<RichshetsCellContents>> multiValuedColumns)
	{
		this.singleValuedColumns = singleValuedColumns;
		this.multiValuedColumns = multiValuedColumns;
	}
	
	public RichdatashetsRow(List<RichshetsCellContents> singleValuedColumns, List<List<RichshetsCellContents>> multiValuedColumns, int originalDataRowIndex)
	{
		this.singleValuedColumns = singleValuedColumns;
		this.multiValuedColumns = multiValuedColumns;
		this.setOriginalDataRowIndex(originalDataRowIndex);
	}

	public List<RichshetsCellContents> getSingleValuedColumns()
	{
		return singleValuedColumns;
	}
	
	public void setSingleValuedColumns(List<RichshetsCellContents> singleValuedColumns)
	{
		this.singleValuedColumns = singleValuedColumns;
	}
	
	public List<List<RichshetsCellContents>> getMultiValuedColumns()
	{
		return multiValuedColumns;
	}
	
	public void setMultiValuedColumns(List<List<RichshetsCellContents>> multiValuedColumns)
	{
		this.multiValuedColumns = multiValuedColumns;
	}
	
	public int getOriginalDataRowIndex()
	{
		return originalDataRowIndex;
	}
	
	public void setOriginalDataRowIndex(int originalDataRowIndex)
	{
		if (originalDataRowIndex < -1)
			throw new IllegalArgumentException();
		
		this.originalDataRowIndex = originalDataRowIndex;
	}
}
