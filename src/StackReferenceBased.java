public class StackReferenceBased implements StackInterface  {
	private Node top; 
	public StackReferenceBased()
	{
		top = null;
	}
  /** Determines whether the stack is empty.
      @return true if the stack is empty, otherwise returns false.
  */
  public boolean isEmpty()
  {
  	return top == null;
  }
  /** Removes all the items from the stack.
  */
  public void popAll()
  {
  	top = null;
  }

  /** Adds an item to the top of a stack.
      @ param newItem is the item to be added.
      @throws StackException when newItem cannot be placed on the stack.
  */
  public void push(Object newItem) throws StackException
  {
  	top = new Node(newItem,top);
  }
  /** Removes the top of a stack.
      @return the item that was added most recently is removed 
      from thestack and returned.
      @throws StackException if the stack is empty.
  */
  public Object pop() throws StackException
  {
	if (!isEmpty())
	{
		Node temp = top;
		top = top.getNext();
		return temp.getItem();
	}
	else
		throw new StackException("Pop on stack empty");
  }
  /** Retrieves the top of a stack.
      @return the item that was added most recently   
      @throws StackException if the stack is empty.
  */
  public Object peek() throws StackException
  {
	if (!isEmpty())
	{
		return top.getItem();
	}
	else
		throw new StackException("peek on empty stack");
  }
}  
