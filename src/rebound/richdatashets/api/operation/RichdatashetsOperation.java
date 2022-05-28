package rebound.richdatashets.api.operation;

import java.util.Date;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import rebound.richdatashets.api.model.RichdatashetsSemanticColumns;
import rebound.richdatashets.api.model.RichdatashetsTable;

@FunctionalInterface
public interface RichdatashetsOperation
{
	/**
	 * Note that (if there is output) its {@link RichdatashetsSemanticColumns column uid-index mapping} need not be the same a the input!  Only that the uid *sets* match up!  (Ie, there are the same UIDs in each, just possibly in a different order.)  See {@link RichdatashetsSemanticColumns#hasSameUIDsIgnoringOrder(RichdatashetsSemanticColumns)}.
	 * 
	 * @param data  this is created especially for this invocation and never used by Datashets again (so feel free to keep hold of it after {@link RichdatashetsConnection#perform(boolean, RichdatashetsOperation)} or similar returns!
	 * @return null meaning don't write anything, otherwise it may be the same Java Object (referencewise identical) as was given to it or not!  (whatever is return is only used briefly, then forgotten once {@link RichdatashetsConnection#perform(boolean, RichdatashetsOperation)} or similar returns, so feel free to keep hold of it afterward or have brought it in from beforehand!)
	 * @throws RuntimeException  anything thrown by this will be caught and handled properly (maintenance will write its changes, but no client code changes will be written)
	 */
	public @Nullable RichdatashetsTable performInMemory(@Nonnull RichdatashetsTable data) throws RuntimeException;
	
	
	
	public static interface RichdatashetsOperationWithDataTimestamp
	extends RichdatashetsOperation
	{
		public @Nullable RichdatashetsTable performInMemory(@Nonnull RichdatashetsTable data, @Nullable Date lastModifiedTimeOfOriginalData) throws RuntimeException;
		
		@Override
		public default RichdatashetsTable performInMemory(RichdatashetsTable data) throws RuntimeException
		{
			return performInMemory(data, null);
		}
	}
}
