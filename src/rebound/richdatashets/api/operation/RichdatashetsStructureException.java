package rebound.richdatashets.api.operation;

public class RichdatashetsStructureException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetsStructureException()
	{
		super();
	}
	
	public RichdatashetsStructureException(String message)
	{
		super(message);
	}
	
	public RichdatashetsStructureException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetsStructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
