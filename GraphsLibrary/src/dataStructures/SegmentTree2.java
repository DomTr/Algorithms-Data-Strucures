package dataStructures;

public class SegmentTree2 {
    int size;
    int[] data;
    public SegmentTree2(int[] a) {
        size = 1;
        while(size < a.length) size*=2;
        data = new int[size*2];
        build(a, 0, 0, size);
    }
    private int f(int a, int b) {
        return Math.max(a, b);
    }
    public void build(int[] a, int idx, int lx, int rx) {
        if (rx - lx == 1) {
            if (lx < a.length) {
                data[idx] = a[lx];
            }
            return;
        }
        int m = (lx + rx)/2;
        build(a, 2*idx+1, lx, m);
        build(a, 2*idx+2, m, rx);
        data[idx] = f(data[2*idx+1], data[2*idx+2]);
    }

    public void modify(int i, int val) {
        modify(i, val, 0, 0, size);
    }
    public void modify(int i, int val, int idx, int lx, int rx) {
        if (rx - lx == 1) {
            data[idx] = val;
            return;
        }
        int m = (lx + rx)/2;
        if (i < m) {
            modify(i, val, 2*idx+1, lx, m);
        } else {
            modify(i, val, 2*idx+2, m, rx);
        }
        data[idx] = f(data[2*idx+1], data[2*idx+2]);
    }
    public int query(int x, int l) {
        return query(x, l, 0, 0, size);
    }
    public int query(int x, int l, int idx, int lx, int rx) {
        if (data[idx] < x) return -1;
        if (rx <= l) return -1;
        if (rx - lx == 1) return lx;
        int m = (lx + rx)/2;
        int res = query(x, l, 2*idx+1, lx, m);
        if (res == -1) {
            res = query(x, l, 2*idx+2, m, rx);
        }
        return res;
    }
    public class Item {
    	int x;
    	public Item(int x) {
    		this.x=x;
    	}
    }
}