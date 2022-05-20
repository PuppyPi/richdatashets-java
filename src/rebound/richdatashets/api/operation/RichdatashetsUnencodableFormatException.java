package rebound.richdatashets.api.operation;

/**
 * Some formatting (eg, superscript/subscript) can't be encoded in a Write operation into the underlying implementation/backing.
 */
public class RichdatashetsUnencodableFormatException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetsUnencodableFormatException()
	{
		super();
	}
	
	public RichdatashetsUnencodableFormatException(String message)
	{
		super(message);
	}
	
	public RichdatashetsUnencodableFormatException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetsUnencodableFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
