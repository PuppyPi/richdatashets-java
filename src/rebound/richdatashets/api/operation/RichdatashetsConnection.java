package rebound.richdatashets.api.operation;

import java.io.IOException;
import javax.annotation.Nullable;
import rebound.richdatashets.api.model.RichdatashetsTableContents;

public interface RichdatashetsConnection
{
	/**
	 * @param performMaintenance  if true, errors and things may be fixed, false it's a truly readonly operation!
	 */
	public default RichdatashetsTableContents read(boolean performMaintenance) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException
	{
		RichdatashetsTableContents[] c = new RichdatashetsTableContents[1];
		perform(performMaintenance, d -> {c[0] = d; return null;});
		return c[0];
	}
	
	
	public default void write(RichdatashetsTableContents contents) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException
	{
		perform(true, d -> contents);
	}
	
	
	public void perform(boolean performMaintenance, @Nullable RichdatashetsOperation operation) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException;
}
