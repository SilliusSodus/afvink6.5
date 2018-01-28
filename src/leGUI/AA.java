package leGUI;

import java.util.Arrays;
import java.util.HashMap;

public class AA {
	static private HashMap<String,String> oneToThree = new HashMap<String,String>();
	static private HashMap<String,String> threeToFull = new HashMap<String,String>();
	static private HashMap<String,String> fullToOne = new HashMap<String,String>();
	String aa;
	
	
	public AA(String aa){
		if(oneToThree.size() < 1){
			initialize();
		}
		this.aa = aa;	
	}
	
	public void getValues(){
		if(aa.length() == 1 && Arrays.asList(oneToThree.keySet()).get(0).contains(aa.toUpperCase())){
			System.out.println(aa + ": " + oneToThree.get(aa.toUpperCase()) + " : " + threeToFull.get(oneToThree.get(aa.toUpperCase())));
		}
		else if(aa.length() == 3 && Arrays.asList(threeToFull.keySet()).get(0).contains(aa.toUpperCase())){
			System.out.println(aa + ": " + threeToFull.get(aa.toUpperCase()) + " : " + fullToOne.get(threeToFull.get(aa.toUpperCase())));
		}
		else if(Arrays.asList(fullToOne.keySet()).get(0).contains(aa.toLowerCase())){
			System.out.println(aa + ": " + fullToOne.get(aa.toLowerCase()) + " : " + oneToThree.get(fullToOne.get(aa.toLowerCase())));
		}
		else{
			System.out.println("I should be doing exceptions more..");
		}
	}
	
	
	
	static private void initialize(){
		final String[] ONE   = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V"};
	    final String[] THREE = { "ALA", "ARG", "ASN", "ASP", "CYS", "GLN", "GLU", "GLY",
	                                    "HIS", "ILE", "LEU", "LYS", "MET", "PHE", "PRO", "SER",
	                                    "THR", "TRP", "TYR", "VAL"
	                                   };
	    final String[] FULL = {"alanine","arginine","asparagine","aspartic","cysteine","glutamine","glutamic","glycine",
	    								"histidine","isoleucine","leucine","lysine","methionine","phenylalanine","proline",
	    								"serine","threonine","tryptophan","tyrosine","valine"};
	    for(int i = 0; i < ONE.length; i++){
	    	oneToThree.put(ONE[i], THREE[i]);
	    	threeToFull.put(THREE[i], FULL[i]);
	    	fullToOne.put(FULL[i], ONE[i]);
	    }
	    
	}

}
