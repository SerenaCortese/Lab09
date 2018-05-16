package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {
	
	private BordersDAO dao;
	
	private BorderIdMap borderMap;
	private CountryIdMap countryMap;
	
	private List<Country> countries;
	private List<Border> borders;
	
	SimpleGraph<Country, DefaultEdge> grafo;
	
	public Model() {
		dao = new BordersDAO();
	
		this.borderMap = new BorderIdMap();
		this.countryMap = new CountryIdMap();
		
		countries = dao.loadAllCountries(countryMap);
		
	}
	
	public List<Country> getCountries() {
		return this.countries;
	}

	public String calcolaConfini(int anno) {
		this.createGraph(anno);
		String s = "";
		for(Country c : this.grafo.vertexSet()) {
			int grado = this.grafo.degreeOf(c);
			if(grado>0)
			s += c.getStateNme() + " numero di stati confinanti: "+ grado+"\n";
		}
		return s;
		
	}
	
	public void createGraph(int anno) {
		borders = dao.getCountryPairs(anno, countryMap, borderMap);
		
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.countries);
		
		for(Border b: this.borders) {
			Country c1 = b.getCountry1();
			Country c2 = b.getCountry2();
			
			if(!c1.equals(c2)) {//non devo fare questo controllo se creo uno pseudografo perché lì loop ammessi
				
				Graphs.addEdgeWithVertices(this.grafo, c1, c2);
			}
		}
	}
	
	public int componentiConnesse(int anno) {
		if(this.grafo == null) {
			this.createGraph(anno);
		}
		
		ConnectivityInspector<Country,DefaultEdge> ci = new ConnectivityInspector<Country,DefaultEdge>(grafo);
		return ci.connectedSets().size();
		
	}

	public List<Country> trovaVicini(Country countryScelto) {
		List<Country> result = new ArrayList<Country>();
		
		GraphIterator<Country, DefaultEdge> iterator =new BreadthFirstIterator<Country, DefaultEdge>(this.grafo,countryScelto);
        
		while (iterator.hasNext()) {
			Country c = iterator.next();
			if(countryScelto.getCCode() != c.getCCode())
            result.add(c);
        }
		
		return result;
	}
	
	//VERSIONE RECURSIVE
	
	public Set<Country> trovaViciniRecursive(Country c) {

		Set <Country> cConnessa=new LinkedHashSet<Country>();
		this.recursive(cConnessa,c);
		return cConnessa;
	}

	private void recursive(Set<Country> cConnessa,Country inizio) {
		
		boolean flag=false;
		if(!cConnessa.contains(inizio)) {
			cConnessa.add(inizio);
			flag=true;
		}
		if(flag) {
			for(Country c:Graphs.neighborListOf(grafo, inizio)) {
				recursive(cConnessa,c);
			
			}
		}
	}


	
	

}
