package com.example.searchin;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
	private class Node {
		private MyFile data;
		private int height;
		private Node parent;
		private Node right;
		private Node left;

		public Node(MyFile data) {
			this.data = data;
			this.height = 0;
			this.parent = null;
			this.left = null;
			this.right = null;
		}

		public int updateHeight() {
			if (left != null && right != null) {
				if (left.getHeight() > right.getHeight())
					height = left.getHeight() + 1;
				else
					height = right.getHeight() + 1;
			} else if (left != null)
				height = left.getHeight() + 1;
			else if (right != null)
				height = right.getHeight() + 1;
			else
				height = 0;
			return height;
		}

		public int getBalance() {
			Node n = this;
			if (n.getLeft() != null && n.getRight() != null)
				return n.getLeft().getHeight() - n.getRight().getHeight();
			else if (n.getLeft() != null)
				return n.getLeft().getHeight() + 1;
			else if (n.getRight() != null)
				return (-1) * (n.getRight().getHeight() + 1);
			else
				return 0;
		}

		public void removeParent() {
			this.parent = null;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public Node getParent() {
			return parent;
		}

		public MyFile getData() {
			return data;
		}

		public int getHeight() {
			return height;
		}

		public Node setLeft(Node left) {
			if (left != null)
				left.parent = this;
			this.left = left;
			updateHeight();
			return this.left;
		}

		public Node setRight(Node right) {
			if (right != null)
				right.parent = this;
			this.right = right;
			this.updateHeight();
			return this.left;
		}
	}

	private Node root;

	public AVLTree() {
		this.root = null;
	}

	public AVLTree(MyFile data) {
		this.root = new Node(data);
	}

	private void setRoot(Node node) {
		root = node;
		if (root != null)
			root.removeParent();
	}

	private void rotateLeft(Node n) {
		Node p = n.getParent();
		boolean side = false;
		if (p != null) {
			if (p.getLeft() == n)
				side = true;
			else
				side = false;
		}
		Node temp = n.getRight();
		n.setRight(temp.getLeft());
		temp.setLeft(n);
		// Now attach the subtree to the parent (or root)
		if (p != null) {
			if (side)
				p.setLeft(temp);
			else
				p.setRight(temp);
		} else {
			setRoot(temp);
		}
	}

	private void rotateRight(Node n) {
		Node p = n.getParent();
		boolean side = false;
		if (p != null) {
			if (p.getLeft() == n)
				side = true;
			else
				side = false;
		}
		Node temp = n.getLeft();
		n.setLeft(temp.getRight());
		temp.setRight(n);
		// Now attach the subtree to the parent (or root)
		if (p != null) {
			if (side)
				p.setLeft(temp);
			else
				p.setRight(temp);
		} else {
			setRoot(temp);
		}
	}

	private void balanceAtNode(Node n) {
		int bal = n.getBalance();
		if (bal > 1) {
			if (n.getLeft().getBalance() < 0)
				rotateLeft(n.getLeft());
			rotateRight(n);
		} else if (bal < -1) {
			if (n.getRight().getBalance() > 0)
				rotateRight(n.getRight());
			rotateLeft(n);
		}
	}

	public boolean insert(MyFile val) {
		Node node = null;
		if (root == null) {
			root = new Node(val);
			return true;
		} else {
			Node temp = root;

			while (true && temp != null && temp.getData() != null) {
				if (temp.getData().compareTo(val) > 0) {
					if ((temp.getLeft()) == null) {
						node = temp.setLeft(new Node(val));
						break;
					} else {
						temp = temp.getLeft();
					}

				} else if (temp.getData().compareTo(val) < 0) {
					if ((temp.getRight()) == null) {
						node = temp.setRight(new Node(val));
						break;
					} else {
						temp = temp.getRight();
					}

				} else {
					return false;
				}
			}
			// The following code is for updating heights and balancing.
			temp = node;
			while (temp != null) {
				temp.updateHeight();
				this.balanceAtNode(temp);
				temp = temp.getParent();
			}

			return true;
		}
	}

	public int height() {
		return root.getHeight();
	}

	@Override
	public String toString() {
		print(root);
		return "";
	}

	private void print(Node temp) {
		if (temp != null) {
			print(temp.getLeft());
			System.out.println(temp.getData().getName());
			print(temp.getRight());
		}
	}

	public void insert(List<?> list) {
		if (list != null)
			if (list.size() > 0 && list.get(0) != null) {
				if (list.get(0) instanceof MyFile)
					for (int i = 0; i < list.size() && list.get(i) != null; i++)
						insert((MyFile) list.get(i));
				else if (list.get(0) instanceof MyContact)
					for (int i = 0; i < list.size() && list.get(i) != null; i++)
						insert((MyContact) list.get(i));
			}
	}

	public ArrayList<MyFile> search(String searchWord) {
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		ArrayList<MyFile> list2 = new ArrayList<MyFile>();

		if (root == null || searchWord.equals(""))
			return list;

		list2 = searchWords(list2, root, searchWord, 0);
		return mergeLists(list2, searchNearWords(list, root, searchWord));
	}
	
	private ArrayList<MyFile> searchWords(ArrayList<MyFile> list, Node node,
			String searchWord, int bestMatch) {
		if (node != null) {
			list = searchWords(list, node.getLeft(), searchWord, bestMatch);
			if (node.getData().getName().toLowerCase()
					.contains(searchWord.toLowerCase())) {
				if (node.getData().equals(searchWord)) {
					list.add(0, node.getData());
					bestMatch++;
				} else if (node.getData().getName()
						.substring(0, searchWord.length()).toLowerCase()
						.equals(searchWord.toLowerCase()))
					list.add(bestMatch, node.getData());
				else if (node.getData().getName().toLowerCase()
						.contains(searchWord.toLowerCase()))
					list.add(node.getData());

			}
			list = searchWords(list, node.getRight(), searchWord, bestMatch);
		}
		return list;
	}

	private ArrayList<MyFile> searchNearWords(ArrayList<MyFile> list,
			Node node, String searchWord) {
		if (node != null) {
			list = searchNearWords(list, node.getLeft(), searchWord);

			if ((int) (Search.compareStrings(searchWord.toLowerCase(), node
					.getData().getName().toLowerCase()) * 100.0) > 30)
				list.add(node.getData());

			list = searchNearWords(list, node.getRight(), searchWord);
		}

		return list;
	}

	public ArrayList<MyFile> toList() {
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		return toList(root, list);
	}

	private ArrayList<MyFile> toList(Node node, ArrayList<MyFile> list) {
		if (node != null) {
			list = toList(node.getRight(), list);
			list.add(node.getData());
			list = toList(node.getLeft(), list);
		}
		return list;
	}
	
	public ArrayList<MyFile> toFileList() {
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		return toFileList(root, list);
	}

	private ArrayList<MyFile> toFileList(Node node, ArrayList<MyFile> list) {
		if (node != null) {
			list = toFileList(node.getRight(), list);
			list.add(node.getData());
			list = toFileList(node.getLeft(), list);
		}
		return list;
	}
	
	public ArrayList<String> searchStrings(String searchWord) {
		ArrayList<String> list = new ArrayList<String>();
		// ArrayList<String> list2 = new ArrayList<String>();

		if (root == null || searchWord.equals(""))
			return list;

		// list = searchWords(list, root, searchWord, 0);
		return searchWords_strings(list, root, searchWord, 0);
	}

	private ArrayList<String> searchWords_strings(ArrayList<String> list, Node node,
			String searchWord, int bestMatch) {
		if (node != null) {
			list = searchWords_strings(list, node.getLeft(), searchWord, bestMatch);
			if (node.getData().getName().toLowerCase()
					.contains(searchWord.toLowerCase())) {
				if (node.getData().equals(searchWord)) {
					list.add(0, node.getData().getNameWithoutExtension());
					bestMatch++;
				} else if (node.getData().getName()
						.substring(0, searchWord.length()).toLowerCase()
						.equals(searchWord.toLowerCase()))
					list.add(bestMatch, node.getData().getNameWithoutExtension());
				else if (node.getData().getName().toLowerCase()
						.contains(searchWord.toLowerCase()))
					list.add(node.getData().getNameWithoutExtension());

			}
			list = searchWords_strings(list, node.getRight(), searchWord, bestMatch);
		}
		return list;
	}

	@SuppressWarnings("unused")
	private ArrayList<String> searchNearWords_strings(ArrayList<String> list,
			Node node, String searchWord) {
		if (node != null) {
			list = searchNearWords_strings(list, node.getLeft(), searchWord);

			if ((int) (Search.compareStrings(searchWord.toLowerCase(), node
					.getData().getName().toLowerCase()) * 100.0) > 30)
				list.add(node.getData().getNameWithoutExtension());

			list = searchNearWords_strings(list, node.getRight(), searchWord);
		}

		return list;
	}
	
	private ArrayList<MyFile> mergeLists(ArrayList<MyFile> list1, ArrayList<MyFile> list2) {
		for(int i=0; i<list2.size(); i++) {
			int j;
			for(j=0; j<list1.size(); j++) 
				if(list1.get(j).equals(list2.get(i)))
					break;
			if(j == list1.size())
				list1.add(list2.get(i));
		}
		return list1;
	}
}
