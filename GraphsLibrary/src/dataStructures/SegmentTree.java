package dataStructures;

public class SegmentTree { 
    int[] data;
    int n;

    public SegmentTree(int[] a, int n){
        this.n = n;
        data = new int[2*n];
        for (int i = n; i < 2*n; i++) {
        	data[i] = a[i-n]; // filling the lowest layer
        }
    }
    public void build() {
        for (int i = n-1; i >= 1; i--) {
            data[i] = f(data[2*i], data[2*i+1]);
        }
    }
    public int f(int a, int b) { // can be any associative function, for example Math.max(), gcd, lcm. f doesn't need to be commutative
        return Math.min(a, b);
    }
    public int query(int l, int r) {
        int res = Integer.MIN_VALUE; // NEUTRAL_ELEMENT
        l+=n;
        r+=(n+1);
        while (l < r) {
            if ((l & 1) == 1) {
                res = f(res, data[l]);
                l++;
            }
            if ((r & 1) == 1) {
                r--;
                res = f(res, data[r]);
            }
            l/=2;
            r/=2;
        }
        return res;
    }
    public void modify(int idx, int val) {
    	idx+=n;
    	data[idx] = val;
    	idx/=2;
    	for(;idx>0;idx/=2) {
    		data[idx] = f(data[2*idx+1], data[2*idx+2]);
    	}
    }
}
