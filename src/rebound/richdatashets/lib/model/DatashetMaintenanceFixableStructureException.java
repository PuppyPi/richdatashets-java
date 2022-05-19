package rebound.richdatashets.lib.model;

/**
 * This is a {@link DatashetStructureException} that wouldn't have been thrown if "performMaintenance" was true!  (ie, if we were allowed to fix structure errors that can be automatically fixed)
 */
public class DatashetMaintenanceFixableStructureException
extends DatashetStructureException
{
	private static final long serialVersionUID = 1l;
	
	public DatashetMaintenanceFixableStructureException()
	{
		super();
	}
	
	public DatashetMaintenanceFixableStructureException(String message)
	{
		super(message);
	}
	
	public DatashetMaintenanceFixableStructureException(Throwable cause)
	{
		super(cause);
	}
	
	public DatashetMaintenanceFixableStructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
