package assignment5_f20;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      exTest();
    }    
  public static void exTest(){
        DiGraph d = new DiGraph();
        d.addNode(1, "0");
        d.addNode(3, "1");
        d.addNode(7, "2");
        d.addNode(8, "3");
        d.addNode(4, "4");
        d.addNode(9, "5");
        d.addNode(0, "6");


        
        d.addEdge(0, "0", "5", 3, null);
        d.addEdge(1, "3", "2", 6, null);
        d.addEdge(2, "4", "0", 1, null);
        d.addEdge(11, "4", "5", 2, null);
        d.addEdge(4, "6", "1", 4, null);




       // System.out.println("numEdges: "+d.numEdges());
        //System.out.println("numNodes: "+d.numNodes());
        
        ShortestPathInfo[] sho = d.shortestPath("0");
        
        for(int i=0; i<sho.length;i++) {
        	System.out.println(sho[i]+" ");        }
        
        
      }
  }
