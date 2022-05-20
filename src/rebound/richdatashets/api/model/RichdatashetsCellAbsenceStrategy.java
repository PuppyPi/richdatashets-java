package rebound.richdatashets.api.model;

import static java.util.Collections.*;
import static java.util.Objects.*;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Multivalued cells in Datashets aren't good identifying "nothingness" XD''<br>
 * So there would normally always be extra {@link RichdatashetsCell#Blank blank cells} at the end.<br>
 * In order to overcome this, we have to use some strategy for determining "nothingness" / "absence".<br>
 * There are many ways of doing this; the cells being empty might not be enough if the empty string has to be meaningful and different than a "null" value that's say,
 *  {@link RichdatashetsCellRun#isStrikethrough() struckthrough}, and especially if a "null" value can be different from an "absent" value (say, background is shaded in gray!).
 *  And so we let the user of datashets (client code) decide how they want to do this in a way that's right for them, on a per-table, per-column basis! :3<br>
 * <br>
 * {@link #isAbsent(RichdatashetsCell)} is used when reading, and {@link #getAbsentValueForNewCells()} is used when writing.
 */
@Immutable
public class RichdatashetsCellAbsenceStrategy
{
	public static final RichdatashetsCellAbsenceStrategy FullyBlankCellStrategy = new RichdatashetsCellAbsenceStrategy(c -> c.isBlank(), RichdatashetsCell.Blank);
	public static final RichdatashetsCellAbsenceStrategy EmptyTextCellStrategy = new RichdatashetsCellAbsenceStrategy(c -> c.isEmptyText(), RichdatashetsCell.Blank);
	public static final RichdatashetsCellAbsenceStrategy EmptyShadedGrayCellStrategy = new RichdatashetsCellAbsenceStrategy(c -> c.isEmptyText() && c.getBackgroundColor() != null && (c.getBackgroundColor().getR() < 250 || c.getBackgroundColor().getG() < 250 || c.getBackgroundColor().getB() < 250), new RichdatashetsCell(singletonList(RichdatashetsCellRun.Blank), null, new RichdatashetsColor(224, 224, 224)));  //*any* color other than white (when text is empty) is considered absent!  (250 not 255 juuuuuuust in case there's some kind of rounding or conversion errors or something ^^''  250 is the lowest Green can go before I notice it's not white and 5 units probably is plenty for rounding errors, so that seems like a fine threshold :3  —PP )
	
	
	protected final @Nonnull Predicate<RichdatashetsCell> isAbsent;
	protected final @Nonnull RichdatashetsCell absentValueForNewCells;
	
	public RichdatashetsCellAbsenceStrategy(Predicate<RichdatashetsCell> isAbsent, RichdatashetsCell absentValueForNewCells)
	{
		this.isAbsent = requireNonNull(isAbsent);
		this.absentValueForNewCells = requireNonNull(absentValueForNewCells);
	}
	
	public boolean isAbsent(RichdatashetsCell v)
	{
		return isAbsent.test(v);
	}
	
	public @Nonnull Predicate<RichdatashetsCell> getIsAbsent()
	{
		return isAbsent;
	}
	
	public @Nonnull RichdatashetsCell getAbsentValueForNewCells()
	{
		return absentValueForNewCells;
	}
}
