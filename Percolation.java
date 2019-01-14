package percolation;

import java.util.Random;

public class Percolation 
{
	private int width;
	private int height;
	private int top;
	private int bottom;
	
	private int[] id;
	private int[] size;
	private boolean[] isOpen;
	
	private Random rand = new Random();
	
	public Percolation(int height, int width)
	{
		//initialize variables
		this.height = height;
		this.width = width;
		this.top = width * height;
		this.bottom = width * height + 1;
		
		//set values in arrays
		id = new int[(height * width) + 2];	
		size = new int[(height * width) + 2];
		isOpen = new boolean[height * width];	
		
		for(int i = 0; i < id.length; i++)
		{
			id[i] = i;
			size[i] = 1;
			if ( i < id.length - 2)
			{
				isOpen[i] = rand.nextBoolean();	
			}
		}
		
		for (int i = 0; i < id.length-2; i++)
		{
			if (isOpen[i]) 
			{
				//connect open squares on first row to the top
				if (i < width)
					union(i, top); 
				else
				{
					//connect open squares to open squares above
					if (isOpen[i - width])
						union(i, i - width);			
					if (i % width != 0)
					{
						//connect open squares to open squares to the left
						if (isOpen[i - 1])
							union(i, i - 1);
					}
				}
			}
		}
		
		joinBottom();
		

	}
	
	public void union(int p, int q)
	{
		 int pRoot = findRoot(p);
		 int qRoot = findRoot(q);
		 
		//check if the root of p and q are different
		//see which tree is smaller
		//set root of smaller tree to root of larger tree
		 if (pRoot != qRoot)
		 {
			 if (size[pRoot] < size[qRoot]) 
			 {
				 id[pRoot] = qRoot;
				 size[qRoot] += size[pRoot]; 
				 //size[pRoot] = ???
			 }
			 else 
			 {
				 id[qRoot] = pRoot; 
				 size[pRoot] += size[qRoot]; 
				 //size[pRoot] = ???
			 } 
		 }
	}
	
	public boolean isConnected(int p, int q)
	{
		//if p and q have the same root then they are connected
		return findRoot(p) == findRoot(q);
	}
	
	public int findRoot(int p)
	{
		//find the root - when the index number is equal to the parent node id
		//if not the root, set the value at p to the value of the parentNode id
		while (p != id[p]) 
			p = id[p];
		return p;
	}
	
	public void joinBottom() //BROKEN!!!!!!!!!!!! squares not attached to the top are being connected at the bottom
	{
		//only connect squares that are open and already connected to the top
		for (int i = isOpen.length - width; i < isOpen.length; i++)
		{
			if ( isOpen[i] == true && isConnected(i, top) )
				union(i, bottom);
		}
		System.out.println();
	}
	
	public boolean percolates()
	{
		return isConnected(top, bottom);
	}
	



	public int[] getId() {
		return id;
	}

	public int[] getSize() {
		return size;
	}

	public boolean[] getIsOpen() {
		return isOpen;
	}

	public int getTop() {
		return top;
	}
	
	public int getBottom() {
		return bottom;
	}
	
	
	
	
	
	
}
