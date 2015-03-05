package nl.hu.fnt.berend;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class HierarchicalNodeIterator implements Iterator<HierarchicalNode> {

	private Queue<HierarchicalNode> queue;

	public HierarchicalNodeIterator(HierarchicalNode node) {
		super();
		queue = new LinkedList<HierarchicalNode>();
		if (queue.offer(node)) {
			next();
		} else {
			throw new IllegalStateException(
					"unable to offer the root to the queue");
		}
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public HierarchicalNode next() {
		HierarchicalNode current = queue.remove();
		Iterator<HierarchicalNode> it = current.children();
		while (it.hasNext()) {
			if (!this.queue.offer(it.next())) {
				throw new IllegalStateException(
						"Unable to add the child to the queue");
			}
		}
		return current;
	}

	public void remove() {
		throw new UnsupportedOperationException("Remove is not supported.");
	}

}
