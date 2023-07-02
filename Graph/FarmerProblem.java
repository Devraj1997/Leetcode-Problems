package graphs.miscellaneous;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class FarmerProblem {

	public static void main(String[] args) {
		compute();
	}
	
	private static void compute() {
		int h = 1;
		State initial = new State(0, 0, 0, 0, true);
		Set<State> visited = new HashSet<>();
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(initial, 0, computeFx(initial)));
		while(!queue.isEmpty()) {
			System.out.print("Open Set: ");
			print(queue);
			Node polled = queue.poll();
			System.out.print("Closed Set: ");
			print(visited);
			visited.add(polled.state);
			if(isGoal(polled.state)) {
				System.out.println("Cost: " + polled.cost);
				return;
			}
			if(polled.state.isValid) {
				System.out.println("Child States:");
				State newState1 = new State(polled.state);
				newState1.farmer = 1 - newState1.farmer;
				newState1.validity();
				
				
				if(!visited.contains(newState1)) {
					int cost = polled.cost + 1;
					int heu = computeFx(newState1);
					System.out.println(newState1 + ", Added with g(b1,b2,b3,b4):" + cost + " and h(b1,b2,b3,b4):" + getHeuristicVal(heu));
					queue.add(new Node(newState1, cost, heu));
				} else System.out.println(newState1 + ", Not Added");
				
				newState1 = new State(polled.state);
				if(newState1.farmer == newState1.fox) {
					newState1.farmer = 1 - newState1.farmer;
					newState1.fox = 1 - newState1.fox;
					newState1.validity();
					if(!visited.contains(newState1)) {
						int cost = polled.cost + 21;
						int heu = computeFx(newState1);
						System.out.println(newState1 + ", Added with g(b1,b2,b3,b4):" + cost + " and h(b1,b2,b3,b4):" + getHeuristicVal(heu));
						queue.add(new Node(newState1, cost, heu));
					} else System.out.println(newState1 + ", Not Added");
				}
				
				newState1 = new State(polled.state);
				if(newState1.farmer == newState1.duck) {
					newState1.farmer = 1 - newState1.farmer;
					newState1.duck = 1 - newState1.duck;
					newState1.validity();
					if(!visited.contains(newState1)) {
						int cost = polled.cost + 11;
						int heu = computeFx(newState1);
						System.out.println(newState1 + ", Added with g(b1,b2,b3,b4):" + cost + " and h(b1,b2,b3,b4):" + getHeuristicVal(heu));
						queue.add(new Node(newState1, cost, heu));
					} else System.out.println(newState1 + ", Not Added");
				}
				
				newState1 = new State(polled.state);
				if(newState1.farmer == newState1.acorn) {
					newState1.farmer = 1 - newState1.farmer;
					newState1.acorn = 1 - newState1.acorn;
					newState1.validity();
					if(!visited.contains(newState1)) {
						int cost = polled.cost + 6;
						int heu = computeFx(newState1);
						System.out.println(newState1 + ", Added with g(b1,b2,b3,b4):" + cost + " and h(b1,b2,b3,b4):" + getHeuristicVal(heu));
						queue.add(new Node(newState1, cost, heu));
					} else System.out.println(newState1 + ", Not Added");
				}
			}
			h++;
			System.out.println("Step " + h + ":");
		}
	}
	
	private static String getHeuristicVal(int heu) {
		if(heu >= 10000) return "Infinity";
		return String.valueOf(heu);
	}
	
	private static int computeFx(State state) {
		int cost = 0;
		if(state.acorn == 0) cost += 6;
		else if(state.acorn==-1) cost += 10000;
		if(state.fox == 0) cost += 21;
		else if(state.fox==-1) cost += 10000;
		if(state.duck==0) cost+=11;
		else if(state.duck==-1) cost += 10000;
		return cost;
	}
	
	private static void print(PriorityQueue<Node> priorityQueue) {
		for(Node node: priorityQueue) {
			System.out.print("{" + node.state + ", g(b1,b2,,b3,b4): " + node.cost + ", h(b1,b2,b3,b4): " + getHeuristicVal(node.heuristic) + ", f(b1,b2,b3,b4):" + 
		(node.cost + node.heuristic > 10000 ? "Infinity" : String.valueOf(node.cost + node.heuristic)) + "},");
		}
		System.out.println();
	}
	
	private static void print(Set<State> visited) {
		System.out.print("(");
		for(State state: visited) {
			System.out.print(state + ", ");
		}
		System.out.println(")");
	}
	
	private static boolean isGoal(State state) {
		return state.farmer == 1 && state.fox == 1 && state.duck == 1 && state.acorn == 1;
	}
}

class State {
	public int farmer;
	public int fox;
	public int duck;
	public int acorn;
	public boolean isValid;
	
	public State(int farmer, int fox, int duck, int acorn, boolean valid) {
		super();
		this.farmer = farmer;
		this.fox = fox;
		this.duck = duck;
		this.acorn = acorn;
		this.isValid = valid;
	}
	
	public State(State state) {
		this.farmer = state.farmer;
		this.fox = state.fox;
		this.duck = state.duck;
		this.acorn = state.acorn;
		this.isValid = true;
	}
	
	public void validity() {
		if(this.fox == this.duck && this.fox!=this.farmer) {
			this.duck = -1;
			this.isValid = false;
		}
		if(this.duck == this.acorn && this.duck!=this.farmer) {
			this.acorn = -1;
			this.isValid = false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acorn;
		result = prime * result + duck;
		result = prime * result + farmer;
		result = prime * result + fox;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (acorn != other.acorn)
			return false;
		if (duck != other.duck)
			return false;
		if (farmer != other.farmer)
			return false;
		if (fox != other.fox)
			return false;
		if (isValid != other.isValid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + getStringValue(farmer) + "," + getStringValue(fox) + "," + getStringValue(duck) + "," + getStringValue(acorn) + ")";
	}
	
	public String getStringValue(int val) {
		if(val == 1) return "L";
		else if(val == 0) return "R";
		return "N"; 
	}
}

class Node implements Comparable<Node> {

	public State state;
	public int cost;
	public int heuristic;
	
	public Node(State s, int c, int heuristic) {
		this.state = s;
		this.cost = c;
		this.heuristic = heuristic;
	}
	
	@Override
	public int compareTo(Node o) {
		return (this.cost + this.heuristic)- o.cost - o.heuristic;
	}
	
}
