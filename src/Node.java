import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Node {

	static int maximumTransaction = 1000;
	int nodeID;
	ArrayList<Node> neighbours;
	ArrayList<Transaction> announcedTransactions;
	ArrayList<Transaction> unannouncedTransactions;
	HashSet<Transaction> hashAnnouncedTransactions;
	HashSet<Transaction> hashUnannouncedTransactions;

	public Node(int nodeID, ArrayList<Node> neighbours) {
		this.nodeID = nodeID;
		this.neighbours = neighbours;
		this.announcedTransactions = new ArrayList<>();
		this.unannouncedTransactions = new ArrayList<>();
		this.hashAnnouncedTransactions = new HashSet<>();
		this.hashUnannouncedTransactions = new HashSet<>();
	}

	public ArrayList<Node> getNeigbours() {
		return this.neighbours;
	}

	public void addNode(Node nxt) {
		this.neighbours.add(nxt);
	}

	public void recieveTransaction(Transaction t) {
//		System.out.println(nodeID);

		if (!hashAnnouncedTransactions.contains(t) && !hashUnannouncedTransactions.contains(t)) {
			this.unannouncedTransactions.add(t);
			this.hashUnannouncedTransactions.add(t);
		}
//		System.out.println("UnAnnounced trans for node " + nodeID);
//		System.out.println(unannouncedTransactions);
	}

	public void announceTransactions() {

		for (Transaction t : unannouncedTransactions) {
			for (Node neighbour : neighbours) {
				neighbour.recieveTransaction(t);
			}
			hashAnnouncedTransactions.add(t);
			announcedTransactions.add(t);
		}
		hashUnannouncedTransactions = new HashSet<>();
		unannouncedTransactions = new ArrayList<>();
		System.out.println("UnAnnounced trans for node " + nodeID);
		System.out.println(unannouncedTransactions);
		System.out.println("Announced trans for node " + nodeID);
		System.out.println(announcedTransactions);

	}
}
