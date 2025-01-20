import java.util.*;
import java .io.*;
class Disjoint{
List<Integer> rank=new ArrayList<>();
List<Integer> parent=new ArrayList<>();
   Disjoint(int n){
   for(int i=0;i<=n;i++){
   rank.add(0);
   parent.add(i);
   }
   }
   //find parent method
   public int findParent(int node){
    if(node==parent.get(node)){
        return node;
    }
    int ulp=findParent(parent.get(node));
    parent.set(node,ulp);
    return parent.get(node);
   }
   public void unionByRank(int u,int v){
    int ulp_u=findParent(u);
    int ulp_v=findParent(v);

    if(ulp_u==ulp_v)return;
    if(rank.get(ulp_u)<rank.get(ulp_v)){
        parent.set(ulp_u, ulp_v);
    }else if(rank.get(ulp_v)<rank.get(ulp_u)){
        parent.set(ulp_v, ulp_u);
    }else{
        parent.set(ulp_v, ulp_u);
        int rankU=rank.get(ulp_u);
        rank.set(ulp_u, rankU+1);
    }
   }
}
public class disjoinset {
    public static void main(String[] args) {
        //disjoin path or we can say it as the union find
      Disjoint d=new Disjoint(7);
      d.unionByRank(1,2);
      d.unionByRank(2,3);
      d.unionByRank(4,5);
      d.unionByRank(6,7);
      d.unionByRank(5,6);
    
      //now we chak the two node have sam eparen tor not\
      if(d.findParent(3)==d.findParent(7)){
        System.out.println("same parent ");
      }else{
        System.out.println("not same parent");
      }
      d.unionByRank(3,7);
      if(d.findParent(3)==d.findParent(7)){
        System.out.println("same parent ");
      }else{
        System.out.println("not same parent");
      }

    }
}
