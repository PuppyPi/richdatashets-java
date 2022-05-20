package rebound.richdatashets.api.model;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Note: Column UIDs are always uppercased for case-insensitivity (really they should be short hex strings, say 8 chars (4 bytes, 32 bits)).<br>
 * This can't be done opaquely for {@link #getUIDs()}.indexOf()/contains()/etc. and {@link #getIndexesByUID()}.get()/containsKey()/etc.<br>
 * 
 * The column indexes here are completely arbitrary but guaranteed to start at 0 and go up to N-1, matching the {@link List} indexes of {@link #getUIDs()}.
 */
@Immutable
public class RichdatashetsSemanticColumns
{
	protected final String[] uids;
	protected final Map<String, Integer> indexesByUID;
	
	
	/**
	 * @param columnUIDs it is copied; a reference to it is not kept.
	 */
	public RichdatashetsSemanticColumns(@Nonnull List<String> columnUIDs)
	{
		int nColumns = columnUIDs.size();
		
		this.uids = columnUIDs.toArray(new String[nColumns]);
		this.indexesByUID = new HashMap<>(nColumns);
		
		for (int i = 0; i < nColumns; i++)
		{
			String uid = columnUIDs.get(i);
			
			if (uid == null)
				throw new NullPointerException();
			
			if (uid.isEmpty())
				throw new IllegalArgumentException();
			
			uid = uid.toUpperCase();
			
			if (indexesByUID.containsKey(uid))
				throw new IllegalArgumentException("Duplicate UIDs!: "+uid);
			
			
			uids[i] = uid;
			indexesByUID.put(uid, i);
		}
	}
	
	
	
	public int size()
	{
		return uids.length;
	}
	
	
	public @Nonnull List<String> getUIDs()
	{
		return unmodifiableList(asList(uids));
	}
	
	public @Nonnull Map<String, Integer> getIndexesByUID()
	{
		return unmodifiableMap(indexesByUID);
	}
	
	
	/**
	 * @param uid case insensitive (auto uppercased)
	 * @return null if not present
	 */
	public @Nullable Integer getIndexByUID(String uid)
	{
		return indexesByUID.get(uid.toUpperCase());
	}
	
	public @Nonnull String getUIDByIndex(int index) throws IndexOutOfBoundsException
	{
		return this.uids[index];
	}
	
	/**
	 * @param uid case insensitive (auto uppercased)
	 * @throws RichdatashetsNoSuchColumnException  if not present
	 */
	public int requireIndexByUID(String uid) throws RichdatashetsNoSuchColumnException
	{
		Integer i = getIndexByUID(uid);
		if (i == null)
			throw new RichdatashetsNoSuchColumnException(uid);
		else
			return i;
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(uids);
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
		RichdatashetsSemanticColumns other = (RichdatashetsSemanticColumns) obj;
		if (!Arrays.equals(uids, other.uids))
			return false;
		return true;
	}
	
	
	public boolean hasSameUIDsIgnoringOrder(RichdatashetsSemanticColumns other)
	{
		return new HashSet<>(this.getUIDs()).equals(new HashSet<>(other.getUIDs()));
	}
	
	public Set<String> getUIDOverlapWith(RichdatashetsSemanticColumns other)
	{
		Set<String> s = new HashSet<>(this.getUIDs());
		s.retainAll(other.getUIDs());
		return s;
	}
}
