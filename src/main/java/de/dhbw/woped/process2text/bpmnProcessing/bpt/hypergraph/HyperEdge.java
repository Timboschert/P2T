package de.dhbw.woped.process2text.bpmnProcessing.bpt.hypergraph;

import de.dhbw.woped.process2text.bpmnProcessing.bpt.hypergraph.abs.AbstractHyperEdge;
import de.dhbw.woped.process2text.bpmnProcessing.bpt.hypergraph.abs.AbstractMultiHyperGraph;
import de.dhbw.woped.process2text.bpmnProcessing.bpt.hypergraph.abs.Vertex;

/**
 * Hyper edge implementation
 * Hyper edge is a collection of vertices of any size
 * Same vertices are allowed within edge to define self-loops
 * 
 * @author Artem Polyvyanyy
 */
public class HyperEdge extends AbstractHyperEdge<Vertex>
{
	@SuppressWarnings("unchecked")
	protected HyperEdge(AbstractMultiHyperGraph g) {
		super(g);
	}
}