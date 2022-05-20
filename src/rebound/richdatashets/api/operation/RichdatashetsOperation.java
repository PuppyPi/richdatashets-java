package rebound.richdatashets.api.operation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import rebound.richdatashets.api.model.RichdatashetsTableContents;

@FunctionalInterface
public interface RichdatashetsOperation
{
	/**
	 * @param data  this is created especially for this invocation and never used by Datashets again (so feel free to keep hold of it after {@link RichdatashetsConnection#perform(boolean, RichdatashetsOperation)} or similar returns!
	 * @return null meaning don't write anything, otherwise it may be the same Java Object (referencewise identical) as was given to it or not!  (whatever is return is only used briefly, then forgotten once {@link RichdatashetsConnection#perform(boolean, RichdatashetsOperation)} or similar returns, so feel free to keep hold of it afterward or have brought it in from beforehand!)
	 * @throws RuntimeException  anything thrown by this will be caught and handled properly (maintenance will write its changes, but no client code changes will be written)
	 */
	public @Nullable RichdatashetsTableContents performInMemory(@Nonnull RichdatashetsTableContents data) throws RuntimeException;
}
