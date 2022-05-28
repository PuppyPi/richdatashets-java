package rebound.richdatashets.api.operation;

import java.io.IOException;
import javax.annotation.Nullable;
import rebound.richdatashets.api.model.RichdatashetsTable;

public interface RichdatashetsConnection
{
	/**
	 * @param performMaintenance  if true, errors and things may be fixed, false it's a truly readonly operation!
	 */
	public default RichdatashetsTable read(boolean performMaintenance) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException
	{
		RichdatashetsTable[] c = new RichdatashetsTable[1];
		perform(performMaintenance, d -> {c[0] = d; return null;});
		return c[0];
	}
	
	
	public default void write(RichdatashetsTable contents) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException
	{
		perform(true, d -> contents);  //if reading the sheet is actually unnecessary if not used by the client code (and thus inefficient)..then override write()!  XD  :3
	}
	
	
	public void perform(boolean performMaintenance, @Nullable RichdatashetsOperation operation) throws RichdatashetsStructureException, RichdatashetsUnencodableFormatException, IOException;
}
