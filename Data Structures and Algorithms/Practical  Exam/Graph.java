public class Graph{
	private  MyDynamicArray<UNode> nodeListU;
	private MyDynamicArray<DNode> nodeListD;

	private class UNode{
		private MyDynamicArray<UNode> connections = new MyDynamicArray<UNode>(UNode.class);

		public UNode(){}

		public void addConnection(UNode node){
			connections.add(node);
		}

		public MyDynamicArray<UNode> getConnections(){
			return connections;
		}
	}
	private class DNode{
		private MyDynamicArray<DNode> connectedTo = new MyDynamicArray<DNode>(DNode.class);
		private MyDynamicArray<DNode> connectedFrom = new MyDynamicArray<DNode>(DNode.class);

		public DNode(){}

		public void addCT(DNode node){
			connectedTo.add(node);
		}

		public void addCF(DNode node){
			connectedFrom.add(node);
		}

		public MyDynamicArray<DNode> getCT(){
			return connectedTo;
		}

		public MyDynamicArray<DNode> getCF(){
			return connectedFrom;
		}
	}

	public Graph(int x){
		if (x == 0){
			
		}
	}
}