class SGTtree{
    int seg[];
    int n;

   public SGTtree(int n){
        this.n=n;
        seg=new int[4 * n];
    }

    // building the tree
   void build(int ind,int low,int high,int arr[]){
    // if we reached the last index or single index
    if(low == high){
        seg[ind]=arr[ind];
        return;
    }
    // find the mid and go to the left and right
    int mid=low+(high-low)/2;
    build(2 * ind+1, low, mid, arr);//left subtree
    build(2 * ind+2, mid+1, high, arr);
    seg[ind]=Math.min(seg[2 * ind+1],seg[2 * ind+2]);//store the minimum 
   }
   
   int querry(int ind,int low,int high,int l,int r){
    // this is the case of no overlap 
    if(l<low || high>r){
        return Integer.MAX_VALUE;
    }
    // complete overlap
    if(low>=l && high<=r){
        return seg[ind];
    }  

    // parital overlap
    int mid=low+(high-low)/2;
    int left=querry(2 * ind+1, low, mid,l,r);//left subtree
    int right=querry(2 * ind+2, mid+1, high, l,r);
      return Math.min(left, right);
   }

   void update(int ind,int low,int high,int i,int val){
    if(low==high){
        seg[ind]=val;
        return;// this is the case for the single element 
    }

    int mid=low+(high-low)/2;
    if(i<=mid) update(2 * ind+1, low, mid, i,val);
    else  update(2 * ind+2, mid+1, high, i,val);
    seg[ind]=Math.min(seg[2 * ind+1],seg[2 * ind+2]);
}
}


public class segementtree {
    public static void main(String[] args) {
        
    }
}
