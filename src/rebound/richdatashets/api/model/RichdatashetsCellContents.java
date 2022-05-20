package rebound.richdatashets.api.model;

import static java.util.Collections.*;
import static java.util.Objects.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * + This doesn't support font family since that's definitely not portable outside of Google Sheets (or the same local spreadsheet program on different computers!)<br>
 * + This doesn't support font size since the size of the font and everything might be a user preference (I mean it's just data; it should probably be all the same size.  The main reason we support anything other than strings is because spreadsheets are terrible at distinguishing empty strings from empty cells (though they can distinguish booleans, ints, floats, dates in cells, they can't usually reliably handle nulls!).<br>
 * <br>
 * + Datashets doesn't support ints because the Google Sheets API makes them be floats sometimes (and JSON officially only supports floats, not ints, being based on Javascript!)<br>
 * + Datashets supports boolean columns, but exposes them to client code as just strings of "true" and "false" (case very insensitive and not guaranteed in reading)!<br>
 */
@Immutable
public class RichdatashetsCellContents
{
	/**
	 * Note that this is just for performance.<br>
	 * There can be multiple instances of blank datashet cells (and indeed, equivalent non-blank ones), just like there can be multiple empty strings or "abc"'s
	 * that are Java Reference-wise different (==) but equivalence-wise the same (.equals(), except that this doesn't support that ^^' )<br>
	 */
	public static final RichdatashetsCellContents Blank = new RichdatashetsCellContents(singletonList(RichdatashetsCellContentsRun.Blank), null, null);
	
	
	public static enum RichdatashetsJustification
	{
		Left,
		Center,
		Right,
	}
	
	/**
	 * The text content of a run is only allowed to be empty if it's the only element in the list, because most spreadsheet programs actually do allow empty-but-formatted cells! (eg, bold and notbold are different even with no text!)
	 * This list must never be empty; if the cell is empty, then use a single run of "" like just mentioned, so that there's not two competing standards for empty-cells X3
	 * + Note that emptiness being "bold" and etc. only works if there is no text in the cell at all in Google Sheets and Gnumeric, so that's what we'll standardize on.
	 */
	protected final @Nonnull List<RichdatashetsCellContentsRun> contents;
	protected final @Nullable RichdatashetsJustification justification;
	protected final @Nullable RichdatashetsColor backgroundColor;
	
	/**
	 * @param contents  an immutable copy is made so no worries about re-using the list provided here
	 * @param justification  null means default based on language (eg, Left for English, Right for Arabic)
	 */
	public RichdatashetsCellContents(List<RichdatashetsCellContentsRun> contents, RichdatashetsJustification justification, RichdatashetsColor backgroundColor)
	{
		requireNonNull(contents);
		
		int n = contents.size();
		if (n == 0)
		{
			throw new IllegalArgumentException("Runs-list must not be empty.");
		}
		else if (contents.size() != 1)
		{
			for (RichdatashetsCellContentsRun r : contents)
				if (r.getContents().isEmpty())
					throw new IllegalArgumentException("No entry in runs-list may have empty text unless it's the only element (which is how an empty-text cell is encoded, since they can have formatting).");
		}
		
		this.contents = unmodifiableList(new ArrayList<>(contents));
		this.justification = justification;
		this.backgroundColor = backgroundColor;
	}
	
	
	
	public boolean isEmptyText()
	{
		return getContents().size() == 1 && getContents().get(0).getContents().isEmpty();
	}
	
	public boolean isBlank()
	{
		return 
		getContents().isEmpty() &&
		getBackgroundColor() == null &&
		getJustification() == null;
	}
	
	public String justText()
	{
		if (getContents().size() == 1)
			return getContents().get(0).getContents();
		else
		{
			StringBuilder b = new StringBuilder();
			for (RichdatashetsCellContentsRun r : getContents())
				b.append(r.getContents());
			return b.toString();
		}
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backgroundColor == null) ? 0 : backgroundColor.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichdatashetsCellContents other = (RichdatashetsCellContents) obj;
		if (backgroundColor == null)
		{
			if (other.backgroundColor != null)
				return false;
		}
		else if (!backgroundColor.equals(other.backgroundColor))
			return false;
		if (contents == null)
		{
			if (other.contents != null)
				return false;
		}
		else if (!contents.equals(other.contents))
			return false;
		if (justification != other.justification)
			return false;
		return true;
	}
	
	
	
	public List<RichdatashetsCellContentsRun> getContents()
	{
		return contents;
	}
	
	public RichdatashetsJustification getJustification()
	{
		return justification;
	}
	
	public RichdatashetsColor getBackgroundColor()
	{
		return backgroundColor;
	}
	
	
	
	@Override
	public String toString()
	{
		return "RichdatashetsCell [contents=" + contents + ", justification=" + justification + ", backgroundColor=" + backgroundColor + "]";
	}
}
