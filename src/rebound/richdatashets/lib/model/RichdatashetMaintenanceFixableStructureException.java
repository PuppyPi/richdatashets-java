package rebound.richdatashets.lib.model;

/**
 * This is a {@link RichdatashetStructureException} that wouldn't have been thrown if "performMaintenance" was true!  (ie, if we were allowed to fix structure errors that can be automatically fixed)
 */
public class RichdatashetMaintenanceFixableStructureException
extends RichdatashetStructureException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetMaintenanceFixableStructureException()
	{
		super();
	}
	
	public RichdatashetMaintenanceFixableStructureException(String message)
	{
		super(message);
	}
	
	public RichdatashetMaintenanceFixableStructureException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetMaintenanceFixableStructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
