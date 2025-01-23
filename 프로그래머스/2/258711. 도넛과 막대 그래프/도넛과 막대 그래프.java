import java.util.*;
import java.io.*;

class Solution {
    class NodeInfo {
        int in = 0;
        List<Integer> edges = new ArrayList<>();
        
        public String toString() {
            return "in : " + in + " / edges : " + edges.toString(); 
        }
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        
        Map<Integer, NodeInfo> nodeList = new HashMap<>();
        
        int[] edge = new int[2];
        int out = 0;
        int in = 0;
        for(int i = 0; i < edges.length; i++){
            edge = edges[i];
            
            out = edge[0];
            in = edge[1];
            
            if(!nodeList.containsKey(out)){
                nodeList.put(out, new NodeInfo());                
            }
            nodeList.get(out).edges.add(in);
            
            if(!nodeList.containsKey(in)){
                nodeList.put(in, new NodeInfo());
            }
            nodeList.get(in).in++;
        }
        
        for(int i : nodeList.keySet()){
            if(nodeList.get(i).in == 0){
                if(nodeList.get(i).edges.size() > 1){
                    answer[0] = i;
                    break;
                }
            }
        }
        
        for(int i = 0; i < nodeList.get(answer[0]).edges.size(); i++){
            nodeList.get(nodeList.get(answer[0]).edges.get(i)).in--;
        }
        
        for(int i : nodeList.keySet()){
            if(nodeList.get(i).in == 0){
                if(i != answer[0]){
                    answer[2]++;
                }
            }else if(nodeList.get(i).in == 2){
                if(nodeList.get(i).edges.size() == 2){
                    answer[3]++;
                }
            }
            // System.out.println(nodeList.get(i));
        }
        
        answer[1] = nodeList.get(answer[0]).edges.size() - answer[2] - answer[3];
        
        return answer;
    }
}