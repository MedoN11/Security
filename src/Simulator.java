import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Simulator {

	static class Pair implements Comparable<Pair> { 
		int u,v;
		public Pair(int u,int v) {
			this.u = u;
			this.v = v;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if(this.u == o.u)
				return this.v - o.v;
			return this.u - o.u;
		}
	}
	static ArrayList<Node> nodes;
	static int leader[];
	public static int getLeader(int u) {
		if (u == leader[u]) {
			return u;
		}
		return leader[u] = getLeader(leader[u]);
	}
	public static void join(int u,int v) {
		u = getLeader(u);
		v = getLeader(v);
		if (u > v) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		leader[v] = u;
	}

	public static boolean isLeader(int u) {
		return getLeader(u) == u;
	}
	public static void constructNodes(int n)  {

		Random box = new Random(4);

		leader = new int[n];
		nodes = new ArrayList();
		for (int i = 0 ; i < n ; ++i) {
			nodes.add(new Node(i , new ArrayList()));
			leader[i] = i;
		}
		int edges = box.nextInt(n * (n - 1) / 2);
		int u = 0, v = 0;
		TreeSet<Pair> set = new TreeSet();
		System.out.println(edges);
		while (edges-- > 0) {

			while (set.contains(new Pair(u,v)) || u == v) {
				u = box.nextInt(n);
				v = box.nextInt(n);
			}
			set.add(new Pair(u,v));
			set.add(new Pair(v,u));
			join(u,v);
			System.err.println(u+" "+v);
			nodes.get(u).addNode(nodes.get(v));
			nodes.get(v).addNode(nodes.get(u));

		}
	
		// handle case of non connected components
		ArrayList<Integer> leaders = new ArrayList();
		for (int i = 0 ; i < n ; ++i) {
			if (isLeader(i)) {
				leaders.add(i);
			}
		}
		for (int i = 0 ; i < leaders.size() - 1;  ++i) {
			int from = leaders.get(i);
			int to = leaders.get(i + 1);
			nodes.get(from).addNode(nodes.get(to));
			nodes.get(to).addNode(nodes.get(from));
      		System.err.println(from+" "+to);

		}

	}

	public static void main(String[]args) throws Throwable {
		int m = 5;
		constructNodes(m);
//		nodes.get(0).announceTransaction(new Transaction(1,0),new TreeSet<Integer>());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Random box = new Random();

		while(true) {
			String cur = br.readLine();
			if(cur.equals("end"))
				break;
			while(!cur.equals("$")) {
				StringTokenizer s = new StringTokenizer(cur, ",");
				Transaction t = new Transaction(new Integer(s.nextToken()), new Integer(s.nextToken()));
				int idx = box.nextInt(m);
				System.err.println("idx"+" "+idx);
				nodes.get(idx).recieveTransaction(t);
				cur = br.readLine();
			}
			for(Node n: nodes) {
				n.announceTransactions();
			}
		}
		
	}
}
