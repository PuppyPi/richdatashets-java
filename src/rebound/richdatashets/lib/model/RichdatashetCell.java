package rebound.richdatashets.lib.model;

import static java.util.Objects.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * + This doesn't support font family since that's definitely not portable outside of Google Sheets (or the same local spreadsheet program on different computers!)<br>
 * + This doesn't support font size since the size of the font and everything might be a user preference (I mean it's just data; it should probably be all the same size.  The main reason we support anything other than strings is because spreadsheets are terrible at distinguishing empty strings from empty cells (though they can distinguish booleans, ints, floats, dates in cells, they can't usually reliably handle nulls!).<br>
 * + This doesn't support partial formatting (eg, part of the cell being bold) because that's quite complex for our use and not portable (it'd be reasonable for a spreadsheet app to not support it)<br>
 * <br>
 * + Datashets doesn't support ints because the Google Sheets API makes them be floats sometimes (and JSON officially only supports floats, not ints, being based on Javascript!)<br>
 * + Datashets supports boolean columns, but exposes them to client code as just strings of "true" and "false" (case very insensitive and not guaranteed in reading)!<br>
 * <br>
 * TODO + This doesn't support a proper {@link #hashCode()} or {@link #equals(Object)} because colors are floating point in google sheets and that would be not always reliable.<br>
 */
@Immutable
public class RichdatashetCell
{
	/**
	 * Note that this is just for performance.<br>
	 * There can be multiple instances of blank datashet cells (and indeed, equivalent non-blank ones), just like there can be multiple empty strings or "abc"'s
	 * that are Java Reference-wise different (==) but equivalence-wise the same (.equals(), except that this doesn't support that ^^' )<br>
	 */
	public static final RichdatashetCell Blank = new RichdatashetCell("", false, false, false, false, null, null);
	
	
	protected final @Nonnull String contents;
	protected final boolean bold;
	protected final boolean underline;
	protected final boolean italic;
	protected final boolean strikethrough;
	protected final @Nullable RichdatashetColor backgroundColor;
	protected final @Nullable RichdatashetColor textColor;
	
	public RichdatashetCell(@Nonnull String contents, boolean bold, boolean underline, boolean italic, boolean strikethrough, RichdatashetColor backgroundColor, RichdatashetColor textColor)
	{
		this.contents = requireNonNull(contents);
		this.bold = bold;
		this.underline = underline;
		this.italic = italic;
		this.strikethrough = strikethrough;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
	}
	
	
	
	public boolean isEmptyText()
	{
		return getContents().isEmpty();
	}
	
	public boolean isBlank()
	{
		return 
		contents.isEmpty() &&
		!bold &&
		!underline &&
		!italic &&
		!strikethrough &&
		backgroundColor == null &&
		textColor == null;
	}
	
	public RichdatashetCell withDifferentText(String newText)
	{
		return new RichdatashetCell(newText, bold, underline, italic, strikethrough, backgroundColor, textColor);
	}
	
	
	
	public String getContents()
	{
		return contents;
	}
	
	public boolean isBold()
	{
		return bold;
	}
	
	public boolean isUnderline()
	{
		return underline;
	}
	
	public boolean isItalic()
	{
		return italic;
	}
	
	public boolean isStrikethrough()
	{
		return strikethrough;
	}
	
	public RichdatashetColor getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public RichdatashetColor getTextColor()
	{
		return textColor;
	}
	
	
	
	
	
	public int hashCodeIgnoringColors()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + (strikethrough ? 1231 : 1237);
		result = prime * result + (underline ? 1231 : 1237);
		return result;
	}
	
	public boolean equalsIgnoringColors(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichdatashetCell other = (RichdatashetCell) obj;
		if (bold != other.bold)
			return false;
		if (contents == null)
		{
			if (other.contents != null)
				return false;
		}
		else if (!contents.equals(other.contents))
			return false;
		if (italic != other.italic)
			return false;
		if (strikethrough != other.strikethrough)
			return false;
		if (underline != other.underline)
			return false;
		return true;
	}



	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backgroundColor == null) ? 0 : backgroundColor.hashCode());
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + (strikethrough ? 1231 : 1237);
		result = prime * result + ((textColor == null) ? 0 : textColor.hashCode());
		result = prime * result + (underline ? 1231 : 1237);
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
		RichdatashetCell other = (RichdatashetCell) obj;
		if (backgroundColor == null)
		{
			if (other.backgroundColor != null)
				return false;
		}
		else if (!backgroundColor.equals(other.backgroundColor))
			return false;
		if (bold != other.bold)
			return false;
		if (contents == null)
		{
			if (other.contents != null)
				return false;
		}
		else if (!contents.equals(other.contents))
			return false;
		if (italic != other.italic)
			return false;
		if (strikethrough != other.strikethrough)
			return false;
		if (textColor == null)
		{
			if (other.textColor != null)
				return false;
		}
		else if (!textColor.equals(other.textColor))
			return false;
		if (underline != other.underline)
			return false;
		return true;
	}



	@Override
	public String toString()
	{
		return "DatashetCell [bold=" + bold + ", underline=" + underline + ", italic=" + italic + ", strikethrough=" + strikethrough + ", backgroundColor=" + backgroundColor + ", textColor=" + textColor + ", contents=" + contents + "]";
	}
}
