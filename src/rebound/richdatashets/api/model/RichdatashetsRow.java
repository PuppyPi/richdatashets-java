package rebound.richdatashets.api.model;

/**
 * Note that, since some rows have extra cell data that's invisible to datashets client code (eg, because its column has no semantic uid; it's just for people not code), we must take care to preserve it!  Hence we have {@link #getOriginalRowIndex()}, the row index in the original
 * spreadsheet starting on the first data, non-header, row at 0.  This is used to copy data from the spreadsheet before overwriting it.  If this value here is -1, then it's treated as a new row that has been
 * added (though not necessarily to the end!  things can be inserted and rearranged however you like and if you take care of these original-indexes, then it won't mess up extra data/formatting :> )
 */
public interface RichdatashetsRow
{
	public int getOriginalRowIndex();
	public void setOriginalRowIndex(int originalDataRowIndex);
}
