package rebound.richdatashets.api.model;

import static java.util.Objects.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class RichdatashetsCellRun
{
	public static final RichdatashetsCellRun Blank = new RichdatashetsCellRun("", false, false, false, false, RichdatashetsCellRunScriptLevel.Normal, null);
	
	
	
	public static enum RichdatashetsCellRunScriptLevel
	{
		Subscript,
		Normal,
		Superscript,
	}
	
	protected final @Nonnull String contents;
	protected final boolean bold;
	protected final boolean italic;
	protected final boolean underline;
	protected final boolean strikethrough;
	protected final @Nonnull RichdatashetsCellRunScriptLevel scriptLevel;
	protected final @Nullable RichdatashetsColor textColor;
	
	public RichdatashetsCellRun(String contents, boolean bold, boolean italic, boolean underline, boolean strikethrough, RichdatashetsCellRunScriptLevel scriptLevel, RichdatashetsColor textColor)
	{
		this.contents = requireNonNull(contents);
		this.bold = bold;
		this.italic = italic;
		this.underline = underline;
		this.strikethrough = strikethrough;
		this.scriptLevel = requireNonNull(scriptLevel);
		this.textColor = textColor;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + ((scriptLevel == null) ? 0 : scriptLevel.hashCode());
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
		RichdatashetsCellRun other = (RichdatashetsCellRun) obj;
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
		if (scriptLevel != other.scriptLevel)
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
	
	public String getContents()
	{
		return contents;
	}
	
	public boolean isBold()
	{
		return bold;
	}
	
	public boolean isItalic()
	{
		return italic;
	}
	
	public boolean isUnderline()
	{
		return underline;
	}
	
	public boolean isStrikethrough()
	{
		return strikethrough;
	}
	
	public RichdatashetsCellRunScriptLevel getScriptLevel()
	{
		return scriptLevel;
	}
	
	public RichdatashetsColor getTextColor()
	{
		return textColor;
	}
	
	
	@Override
	public String toString()
	{
		return "RichdatashetsCellRun [contents=" + contents + ", bold=" + bold + ", italic=" + italic + ", underline=" + underline + ", strikethrough=" + strikethrough + ", scriptLevel=" + scriptLevel + ", textColor=" + textColor + "]";
	}
}
