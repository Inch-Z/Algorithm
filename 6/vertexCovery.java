package Experiment;
import java.util.*;
public class vertexCovery {
	Vertex[] nodes;
	int[][] edge;{
		edge = new int[][]{
			{1, 6}, {4, 2}, {2, 5}, {3, 6},
			{4, 5}, {4, 6}, {6, 7}
		};
	}
	int[] weight;{
		weight = new int[] {
			1, 100, 1, 1, 1, 100, 10
		};
	}
	boolean[] visited;{
		visited = new boolean[edge.length];
		Arrays.fill(visited, true);
	}
	vertexCovery(){
		int verNum = weight.length;
		
		nodes = new Vertex[verNum];
		for(int i = 0; i < verNum; i ++)
			nodes[i] = new Vertex(i, weight[i]);
		
		for(int i = 0; i < edge.length; i ++) {
			int idx1 = edge[i][0];
			int idx2 = edge[i][1];
			nodes[idx1 - 1].addEdge();
			nodes[idx2 - 1].addEdge();
		}
		
		Arrays.sort(nodes);
	}
	
	Queue<State> queue;
	public void solution() {
		queue = new LinkedList<>();
		queue.add(new State(visited, edge.length));
		queue.add(null);
		for(int i = 1, totE = edge.length * 2; i < weight.length;) {
			State state = queue.remove();
			if(state == null) {
				i ++; totE -= nodes[i].edgeNum; 
				continue;
			}
			if(state.leftEdge == 0){
				for(Integer idx : state.record)
					System.out.print(idx);
				return;
			}
			if(state.leftEdge < totE)
				queue.add(new State(state, 0, 0));
			checkState(state, nodes[i], i);
			queue.add(new State(state, 1, nodes[i].weight));
		}
	}
	
	public void checkState(State state, Vertex ver, int i) {
		if(state.record.size() == 0) return;
		int idx = nodes[i].idx;
		for(int k = 0; k < edge.length; k ++) {
			if(edge[k][0] == idx + 1 || edge[k][1] == idx + 1)
				if(state.visR[idx]) {
					state.visR[idx] = false;
					state.leftEdge --;
				}
		}
		
	}
	
	public static void main(String[] args) {
		vertexCovery vc = new vertexCovery();
		
		for(Vertex v : vc.nodes)
			v.printNode();
	
		vc.solution();
	}	
}

class Vertex implements Comparable<Vertex>{
	int idx, weight, edgeNum = 0;
	Vertex(int i, int w){
		idx = i;
		weight = w;
	}
	public void addEdge() {
		edgeNum++;	
	}
	public void printNode() {
		System.out.println("Vertex" + idx + ", " 
						+ weight + " " + edgeNum);
	}
	@Override
	public int compareTo(Vertex v) {
		double w = (double)(this.edgeNum) / this.weight;
		double nW = (double)(v.edgeNum) / v.weight;
		if(w > nW) return -1;
		else return 1;
	}
}

class State{
	int leftEdge, totWei;
	boolean[] visR;
	List<Integer> record;
	State(State s, int choice, int w){
		visR = Arrays.copyOf(s.visR, s.visR.length);
		record = new ArrayList<>(s.record);
		if(choice != -1) record.add(choice);
		else if(choice == 1) {
			totWei = s.totWei + w;
		}
		else {
			totWei = s.totWei;
		}
		leftEdge = s.leftEdge;
		
	}
	State(boolean[] visited, int le){
		visR = Arrays.copyOf(visited, visited.length);
		record = new ArrayList<>();
		leftEdge = le;
		totWei = 0;
	}
}
