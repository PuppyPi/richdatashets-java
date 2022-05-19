package rebound.richdatashets.lib.model;

public class DatashetStructureException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public DatashetStructureException()
	{
		super();
	}
	
	public DatashetStructureException(String message)
	{
		super(message);
	}
	
	public DatashetStructureException(Throwable cause)
	{
		super(cause);
	}
	
	public DatashetStructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
