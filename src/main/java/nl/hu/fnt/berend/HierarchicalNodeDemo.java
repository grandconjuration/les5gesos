package nl.hu.fnt.berend;

public class HierarchicalNodeDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HierarchicalNode root = new HierarchicalNodeImpl("root node");
		
		HierarchicalNode child1 = new HierarchicalNodeImpl(root, "child node A");
		
		HierarchicalNode child2 = new HierarchicalNodeImpl(root, "child node B");
		
		HierarchicalNode child = child1;
		for (int i = 0; i < 5; i++) {
			child = new HierarchicalNodeImpl(child, "child A"+i);
		}
		
		child = child2;
		for (int i = 0; i < 5; i++) {
			child = new HierarchicalNodeImpl(child, "child B"+i);
		}
		
		System.out.println("Path: "+root.getPath());
		for (HierarchicalNode hierarchicalNode : root) {
			System.out.println("Path: "+hierarchicalNode.getPath());
		}
	}
}
