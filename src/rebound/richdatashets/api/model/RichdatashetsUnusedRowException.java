package rebound.richdatashets.api.model;

/**
 * You tried to access a cell in an {@link RichdatashetsUnusedRow absent row}!
 */
public class RichdatashetsUnusedRowException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetsUnusedRowException()
	{
		super();
	}
	
	public RichdatashetsUnusedRowException(String message)
	{
		super(message);
	}
	
	public RichdatashetsUnusedRowException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetsUnusedRowException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
