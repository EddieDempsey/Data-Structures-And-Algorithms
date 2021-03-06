package algo.graphs.dfs.undirected;

import java.util.Collection;
import java.util.LinkedList;

import ds.graphs.IConnectedComponents;
import ds.graphs.Graph;
import edu.princeton.cs.introcs.In;

/**
 * Finding connected components using DFS
 */
public class ConnectedComponents extends AllDFS implements IConnectedComponents
{
	int componentCount;
	int id[];

	/**
	 * {@link DFS#DFS(Graph)}
	 */
	public ConnectedComponents(Graph G)
	{
		super(G);
	}
	
	public void postSourceDFS(Graph G, int source)
	{
		componentCount++;
	}
	
	public void allDFSInitialize(Graph G)
	{
		componentCount = 0;
		id = new int[G.V()];
	}

	public void preAnyAdjacentVerticesVisit(Graph G, int source)
	{
		id[source] = componentCount; 
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ConnectedComponents#connected(int, int)
	 */
	@Override
	public boolean connected(int v, int w)
	{
		return id[v] == id[w];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ConnectedComponents#componentCount()
	 */
	@Override
	public int componentCount()
	{
		return componentCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ConnectedComponents#id(int)
	 */
	@Override
	public int id(int v)
	{
		return id[v];
	}

	/**
	 * A test client
	 * @param args {@code args[0]} = Input-file 
	 */
	@SuppressWarnings({ "unchecked"})
	public static void main(String[] args)
	{
		Graph G = null;
		try
		{
			G = new Graph(new In(args[0]));
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(1);
		}

		IConnectedComponents cc = new ConnectedComponents(G);

		int M = cc.componentCount();
		System.out.println(M + " components");

		Collection<Integer>[] components;
		components = (Collection<Integer>[]) new LinkedList[M];
		for (int i = 0; i < M; i++)
			components[i] = new LinkedList<Integer>();

		for (int v = 0; v < G.V(); v++)
			components[cc.id(v)].add(v);

		for (int i = 0; i < M; i++)
		{
			for (int v : components[i])
				System.out.print(v + " ");
			System.out.println();
		}

	}

	@Override
	public void postEachSourceDFS(int source)
	{
		// TODO Auto-generated method stub
		
	}
}
