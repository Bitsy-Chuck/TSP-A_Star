import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class node2 {
	private int fromNode;
	private int toNode;
	private int cost;

	public node2(int from, int to, int cost){
		this.fromNode = from;
		this.toNode = to;
		this.cost = cost;
	}

	public int getCost(){
		return this.cost;
	}
	public int getFromNode(){
		return this.fromNode;
	}
	public int getToNode(){
		return this.toNode;
	}
}


class distComparator implements Comparator<node2> {
	public int compare(node2 n1, node2 n2){
		if(n1.getCost() < n2.getCost())
			return -1;
		else if(n1.getCost() > n2.getCost())
			return 1;
		else
			return 1;
	}
}


class minSpanTree {
	private ArrayList<node2> tree;
	private Comparator<node2> comparator;
	private PriorityQueue<node2> q;

	public minSpanTree(int [][] cost, int visited[], int numOfCities){
		tree = new ArrayList<node2>();
		comparator = new distComparator();
		q = new PriorityQueue<node2>(numOfCities, comparator);

		for(int i = 0; i < numOfCities; i++){								//TC: we are taking upper triangular matrix
			for(int j = i + 1; j < numOfCities; j++){
				if(visited[i] == 0 && visited[j] == 0){
					node2 tempEdge = new node2(i, j, cost[i][j]);
					q.add(tempEdge);
				}
			}
		}
		for(int i = 0, tempNum = numOfCities; i < tempNum; i++){
			if(visited[i] > 0)
				numOfCities--;
		}
		this.calMinSpanTree(cost, numOfCities);
	}

	private void calMinSpanTree(int [][] cost, int numOfCities){
		node2 tempEdge;
		int i = 0;
		while((tempEdge = q.poll()) != null  && i < numOfCities-1){
			System.out.println(tempEdge.getFromNode() + "->" + tempEdge.getToNode() + "(" + tempEdge.getCost()+")");
			if( !this.isCycle(tempEdge)){
				tree.add(tempEdge);
				i++;
			}
		}
	}

	private boolean isCycle(node2 newEdge){
		boolean node1Match, node2Match;
		node1Match = node2Match = false;
		Iterator<node2> navi = this.tree.iterator();
		while(navi.hasNext()){
			node2 tempEdge = navi.next();
			if(!node1Match && (newEdge.getFromNode() == tempEdge.getFromNode() || newEdge.getFromNode() == tempEdge.getToNode())){
				node1Match = true;
			}
			if(!node2Match && (newEdge.getToNode() == tempEdge.getFromNode() || newEdge.getToNode() == tempEdge.getToNode())){
				node2Match = true;
			}
		}
		if(node1Match && node2Match)
			return true;
		else
			return false;
	}

	public int getTotalCost(){
		int totalCost = 0;
		Iterator<node2> navi = this.tree.iterator();
		while(navi.hasNext()){
			node2 tempEdge = navi.next();
			totalCost += tempEdge.getCost();
			System.out.println(tempEdge.getFromNode() + "->" + tempEdge.getToNode() + "(" + tempEdge.getCost() + ")");
		}
		return totalCost;
	}
}
