package nl.hu.fnt.berend;

import java.util.Iterator;

public interface HierarchicalNode extends Iterable<HierarchicalNode> {
	HierarchicalNode getParent();

	boolean addChild(HierarchicalNode node);

	boolean removeChild(HierarchicalNode node);

	Iterator<HierarchicalNode> children();

	String getPath();

	String getName();
}
