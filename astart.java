import java.util.*;
import java.io.*;
class node1 {
	int cityNo;
	int gcost;

	node1(int cno, int c){
		this.cityNo = cno;
		this.gcost = c;
	}
}

class comparator implements Comparator<node1> {
	public int compare(node1 a, node1 b) {
		if(a.gcost > b.gcost)
			return 1;
		else
			return -1;
	}
}

class astar{
  private PriorityQueue<node1> openList;
  static private int[] path;
  private int costInc;
  private int[] visited;
  private Comparator<node1> comparator;
  public astar(int[][] cost, int noOfCities, int start){
    this.path= new int[noOfCities];
    this.visited= new int[noOfCities];
    for(int i=0;i<noOfCities;i++)
      visited[i]=0;
    comparator= new comparator();
    openList= new PriorityQueue<node1>(noOfCities, comparator);

    this.findanswer(cost, noOfCities, start);
  }
  void printPath(){
		System.out.println();
    for(int i = 0; i < this.path.length; i++){
      System.out.print(path[i] + " ");
    }
    System.out.print("\t(" + this.costInc + ")");
		System.out.println();
  }
  private void findanswer(int[][] cost, int noOfCities, int start){
    int curr= start;
    int noVisited=1;
    visited[curr]=noVisited++;//to keep them in order so that we can easily print which comes first
    for(;noVisited <= noOfCities;){
      for(int i=0;i<noOfCities;i++){
        if((i!=curr) && (cost[curr][i]!= Integer.MAX_VALUE)){
          if(visited[i]==0){
            int hn=0;//should change the value
						minSpanTree tempSpanTree = new minSpanTree(cost, visited, noOfCities);
						hn= tempSpanTree.getTotalCost();
            node1 temp= new node1(i, (cost[i][curr]+ hn));
            openList.add(temp);
          }
        }
      }
      curr=openList.poll().cityNo;
      visited[curr]=noVisited++;
    }
    //calc path
    for(int i = 1; i <= this.visited.length; i++){
			for(int j = 0; j < this.visited.length; j++)
				if(visited[j] == i){
					this.path[i-1] = j + 1;
				}
		}
		long tempTotalCost = 0;
		for(int i = 0; i < this.visited.length-1; i++){
      try{
        tempTotalCost += cost[path[i]-1][path[i+1]-1];
      }
      catch(Exception e){
        System.out.println("error");
        e.printStackTrace();
      }
		}
		if(tempTotalCost > Integer.MAX_VALUE)
			costInc = Integer.MAX_VALUE;
		else
			costInc = (int) tempTotalCost;
		System.out.println("\n");
		for(int i = 0; i < this.visited.length; i++){
			System.out.printf("%d ( %d )\t", i, visited[i]);
		System.out.println();
		}
    printPath();
  }

}

class AStarSolver {
	public static void main(String[] args) {
		Scanner sc;
		try {
			sc = new Scanner(new File("inp.txt"));
			int numOfCities = sc.nextInt();;
			int [][] cost = new int[numOfCities][numOfCities];
			for(int i = 0; i < numOfCities; i++)
				for(int j = 0; j < numOfCities && sc.hasNextInt(); j++){
					int n = sc.nextInt();
					if(n < 0)							//not connected
						cost[i][j] = Integer.MAX_VALUE;
					else
						cost[i][j] = n;
				}
			astar tspSolver = new astar(cost, numOfCities, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
}
