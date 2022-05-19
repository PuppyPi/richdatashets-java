package rebound.richdatashets.lib.model;

import static java.util.Objects.*;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Multivalued cells in Datashets aren't good identifying "nothingness" XD''<br>
 * So there would normally always be extra {@link DatashetCell#Blank blank cells} at the end.<br>
 * In order to overcome this, we have to use some strategy for determining "nothingness" / "absence".<br>
 * There are many ways of doing this; the cells being empty might not be enough if the empty string has to be meaningful and different than a "null" value that's say,
 *  {@link DatashetCell#isStrikethrough() struckthrough}, and especially if a "null" value can be different from an "absent" value (say, background is shaded in gray!).
 *  And so we let the user of datashets (client code) decide how they want to do this in a way that's right for them, on a per-table, per-column basis! :3<br>
 * <br>
 * {@link #isAbsent(DatashetCell)} is used when reading, and {@link #getAbsentValueForNewCells()} is used when writing.
 */
@Immutable
public class DatashetCellAbsenceStrategy
{
	public static final DatashetCellAbsenceStrategy FullyBlankCellStrategy = new DatashetCellAbsenceStrategy(c -> c.isBlank(), DatashetCell.Blank);
	public static final DatashetCellAbsenceStrategy EmptyTextCellStrategy = new DatashetCellAbsenceStrategy(c -> c.isEmptyText(), DatashetCell.Blank);
	public static final DatashetCellAbsenceStrategy EmptyShadedGrayCellStrategy = new DatashetCellAbsenceStrategy(c -> c.isEmptyText() && c.getBackgroundColor() != null && (c.getBackgroundColor().getR() < 0.99 || c.getBackgroundColor().getG() < 0.99 || c.getBackgroundColor().getB() < 0.99), new DatashetCell("", false, false, false, false, new DatashetsColor(224, 224, 224), null));  //*any* color other than white (when text is empty) is considered absent!
	
	
	protected final @Nonnull Predicate<DatashetCell> isAbsent;
	protected final @Nonnull DatashetCell absentValueForNewCells;
	
	public DatashetCellAbsenceStrategy(Predicate<DatashetCell> isAbsent, DatashetCell absentValueForNewCells)
	{
		this.isAbsent = requireNonNull(isAbsent);
		this.absentValueForNewCells = requireNonNull(absentValueForNewCells);
	}
	
	public boolean isAbsent(DatashetCell v)
	{
		return isAbsent.test(v);
	}
	
	public @Nonnull Predicate<DatashetCell> getIsAbsent()
	{
		return isAbsent;
	}
	
	public @Nonnull DatashetCell getAbsentValueForNewCells()
	{
		return absentValueForNewCells;
	}
}
