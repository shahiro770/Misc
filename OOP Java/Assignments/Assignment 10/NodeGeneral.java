
public class NodeGeneral{
	private Object value;
	private NodeGeneral next;
	
	public NodeGeneral(Object value, NodeGeneral nextNode){
		this.value = value;
		this.next = nextNode;
	}
	
	public Object getValue(){
		return value;
	}

	public NodeGeneral getNext(){
		return next;
	}
	
	public void setValue(Object value){
		this.value = value;
	}
	
	public void setNext(NodeGeneral next){
		this.next = next;
	}
}