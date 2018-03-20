package binarysortstuff;

public class BSTDictionary<E, K extends Sortable> implements Dictionary<E,K>{

	BSTNode<E,K> root; //this is the root of the BST
	
	@Override
	public Object search(Sortable key) {

		BSTNode<E, K> nodeFound;
		nodeFound = findRecursive(root,key);
		if(nodeFound == null) {
			return null; //not found
		}
			else return nodeFound.getElement(); //call another helper method.
	}
	
	
	/**
	 * helper method to find a node, starting at the root of the BST
	 * @param node node to start at, is always the root initially 
	 * @param key key to be found
	 * @return the found node
	 */
	private BSTNode<E,K> findRecursive(BSTNode<E,K> node, Sortable key){
		
		//if the start is non existent, then return null
		if(node == null) {
			return null;
		}
		
		//if the current key is greater than the node
		if(key.compareTo(node.getKey()) > 0) {
			
			//go right, with a new starting position
			return findRecursive(node.getRight(),key);
			
		} else if(key.compareTo(node.getKey())==0) {
			//we're at the node we needed to find so return
			return node;
			
		} else {
			//go left, with a new starting position
			return findRecursive(node.getLeft(),key);
		}
	}
		

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insert(Sortable key, Object element) {
		
		// there are no items in the BST
		// make a the new BSTNode the root
		if(root == null) {
			root = new BSTNode(key, element, null, null);
		}
		// insert new BST node
		else {
			insertRecursive(root, key, element);
}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Helper method to insert a node recursively 
	 * @param node starting node
	 * @param key key of newly added node
	 * @param element element of newly added node
	 */
	public void insertRecursive(BSTNode<E,K> node, Sortable key, Object element) {
		
				//if the two keys are equal
				if(key.compareTo(node.getKey()) == 0) {
					//cannot have duplicates
				}
				//if key is greater than the node's key
				else if(key.compareTo(node.getKey()) > 0) {
					//greater side goes to the right
					//if right is null, set a new right
					if(node.getRight() == null) {
						node.setRight(new BSTNode(key, element, null, null));
					}
					//otherwise insert a new right until we get to a right thats null
					else {
						insertRecursive(node.getRight(), key, element);
					}
				}
				
				//if key is less than the node's key
				else if(key.compareTo(node.getKey()) < 0) {
					//Lesser side goes to the left
					//if left is null, set a new left
					if(node.getLeft() == null) {
						node.setLeft(new BSTNode(key, element, null, null));
					}
					//otherwise insert a new left until we get to a left thats null
					else insertRecursive(node.getLeft(), key, element);
				}
	}
	
	@Override
	public void delete(Sortable key) {
		// TODO Auto-generated method stub
		BSTNode<E,K> temp = findRecursive(root,key);
		if(temp != null) {
			root = deleteNode(root,key);
		}
		
		
	}
	
	/**
	 * Helper method to delete a node
	 * @param node node to start at
	 * @param key key of node to delete
	 * @return deleted node
	 */
	private BSTNode<E,K> deleteNode(BSTNode<E,K> node, Sortable key){
		if(node == null) {
			return null;
		}
		
		if(key.compareTo(node.getKey()) == -1) {
			node.setLeft(deleteNode(node.getLeft(), key));
		} else if(key.compareTo(node.getKey()) == 1) {
			node.setRight(deleteNode(node.getRight(), key));
		} else {
			if(node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				BSTNode<E,K> temp = minVal(node.getRight());
				
				node.setElement(temp.getElement());
				node.key = temp.key;
				node.setRight(delMin(node.getRight()));
			}
		}
		return node;
		}
	
	private BSTNode<E,K> delMin(BSTNode<E,K> node){
		if(node.getLeft() == null) {
			return node.getRight();
		} else {
			node.setLeft(delMin(node.getLeft()));
			return node;
		}
	}
	
	/**
	 * Get minimum value in the BST
	 * @param node node to start at
	 * @return node with the smallest value
	 */
	private BSTNode<E,K> minVal(BSTNode<E,K> node) {
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

	@Override
	public void printTree() {
		traverseOrder(root); //use helper method
		
	}

	@Override
	public int depth() {

		if(root == null) {
			return 0; //if it is empty then the tree is empty
		} else {
			return maxDepth(root);
		}
	}
	
	/**
	 * Helper method to calculate the maximum depth of a BST
	 * @param node node to start at, is initially the root
	 * @return the max depth of the BST
	 */
	public int maxDepth(BSTNode<E,K> node) {
		
		if(node == null) {
			return 0;
		} else {
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);
			
			if(lDepth > rDepth) {
				return (lDepth + 1);
			} else {
				return (rDepth + 1);
			}
		}
		
	}
	/**
	 * traverse the BST in order of value
	 * @param node node to start at
	 */
	public void traverseOrder(BSTNode<E,K> node) {
		if(node != null) { //make sure that the start isn't null
			traverseOrder(node.getLeft());
			System.out.println(" " + node.getElement().toString() + " " + node.getKey().toString());
			traverseOrder(node.getRight());
		}
	}

}
