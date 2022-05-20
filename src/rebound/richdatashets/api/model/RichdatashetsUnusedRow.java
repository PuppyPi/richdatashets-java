package rebound.richdatashets.api.model;

/**
 * Unused rows ("blank" rows but truly blank, themselves, not just full of nulls!) still need to track the original index they came from in case there's information (eg, formatting, metadata, etc.) that isn't present in our Datashets form!
 */
public class RichdatashetsUnusedRow
implements RichdatashetsRow
{
	protected int originalRowIndex;  //-1, 0, 1, 2, etc.
	
	public RichdatashetsUnusedRow()
	{
		this(-1);
	}
	
	public RichdatashetsUnusedRow(int originalRowIndex)
	{
		this.originalRowIndex = originalRowIndex;
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
