package rebound.richdatashets.api.model;

import java.util.List;

public class RichdatashetsUsedRow
implements RichdatashetsRow
{
	protected List<RichdatashetsCellContents> singleValuedColumns;
	protected List<List<RichdatashetsCellContents>> multiValuedColumns;
	protected int originalRowIndex = -1;  //-1, 0, 1, 2, etc.
	
	public RichdatashetsUsedRow()
	{
		super();
	}
	
	/**
	 * @param singleValuedColumns  live reference to it is kept!
	 * @param multiValuedColumns  live reference to it is kept!
	 */
	public RichdatashetsUsedRow(List<RichdatashetsCellContents> singleValuedColumns, List<List<RichdatashetsCellContents>> multiValuedColumns)
	{
		this.singleValuedColumns = singleValuedColumns;
		this.multiValuedColumns = multiValuedColumns;
	}
	
	public RichdatashetsUsedRow(List<RichdatashetsCellContents> singleValuedColumns, List<List<RichdatashetsCellContents>> multiValuedColumns, int originalDataRowIndex)
	{
		this.singleValuedColumns = singleValuedColumns;
		this.multiValuedColumns = multiValuedColumns;
		this.setOriginalRowIndex(originalDataRowIndex);
	}
	
	public List<RichdatashetsCellContents> getSingleValuedColumns()
	{
		return singleValuedColumns;
	}
	
	public void setSingleValuedColumns(List<RichdatashetsCellContents> singleValuedColumns)
	{
		this.singleValuedColumns = singleValuedColumns;
	}
	
	public List<List<RichdatashetsCellContents>> getMultiValuedColumns()
	{
		return multiValuedColumns;
	}
	
	public void setMultiValuedColumns(List<List<RichdatashetsCellContents>> multiValuedColumns)
	{
		this.multiValuedColumns = multiValuedColumns;
	}
	
	@Override
	public int getOriginalRowIndex()
	{
		return originalRowIndex;
	}
	
	@Override
	public void setOriginalRowIndex(int originalDataRowIndex)
	{
		if (originalDataRowIndex < -1)
			throw new IllegalArgumentException();
		
		this.originalRowIndex = originalDataRowIndex;
	}
}
