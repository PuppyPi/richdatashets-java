package rebound.richdatashets.api.operation;

/**
 * This is a {@link RichdatashetsStructureException} that wouldn't have been thrown if "performMaintenance" was true!  (ie, if we were allowed to fix structure errors that can be automatically fixed)
 */
public class RichdatashetsMaintenanceFixableStructureException
extends RichdatashetsStructureException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetsMaintenanceFixableStructureException()
	{
		super();
	}
	
	public RichdatashetsMaintenanceFixableStructureException(String message)
	{
		super(message);
	}
	
	public RichdatashetsMaintenanceFixableStructureException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetsMaintenanceFixableStructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
