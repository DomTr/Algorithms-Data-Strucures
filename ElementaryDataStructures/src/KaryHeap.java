
public class KaryHeap {
	private int[] values;
    private int dimension;
    private int indOfLast;
    public KaryHeap(int[] values, int dimension) {
        this.values = new int[values.length];
        this.dimension = dimension;
        this.indOfLast = values.length - 1;
        for(int i = 0 ; i < values.length; i++){
            this.values[i] = values[i];
        }

        for(int i = (values.length-1)/dimension; i >= 0; i--){
            if(getChildIndex(0, i) > indOfLast) continue;
            siftDown(i);
        }
    }
    public KaryHeap(int[] values, int dimension, int maxSize) {
        if (maxSize > values.length) {
            this.values = new int[maxSize];
        } else {
            this.values = new int[values.length];
        }
        this.dimension = dimension;
        this.indOfLast = values.length - 1;
        for(int i = 0 ; i < values.length; i++){
            this.values[i] = values[i];
        }

        for(int i = (values.length-1)/dimension; i >= 0; i--){
            if(getChildIndex(0, i) > indOfLast) continue;
            siftDown(i);
        }
    }
    public int[] getHeap() {
        return values;
    }
    public int getParentIndex(int index) {
        return Math.max((index-1)/dimension, 0);
    }

    public int getChildIndex(int childIndex, int nodeIndex) {
        return dimension * nodeIndex + childIndex + 1;
    }

    public void insert(int value) {
        indOfLast++;
        values[indOfLast] = value;
        siftUp(value);
    }
    private void siftUp(int value) {
        int currInd = indOfLast;
        int parentInd = getParentIndex(currInd);
        while(values[parentInd] < values[currInd]){
            swap(currInd, parentInd);
            currInd = parentInd;
            parentInd = getParentIndex(currInd);
        }
    }
    private void swap(int i, int j){
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
    public int extractMax() {
        int max = values[0];
        swap(0, indOfLast);
        indOfLast--;
        siftDown(0);
        return max;
    }

    private void siftDown(int index){
        int mx = -1;
        for(int i = getChildIndex(0, index); i <= Math.min(getChildIndex(dimension-1, index), indOfLast); i++){
            if(values[index] < values[i] && (mx == -1 || values[mx] < values[i])){
                mx = i;
            }
        }
        if(mx != -1 && values[index] < values[mx]){
            swap(index, mx);
            siftDown(mx);
        }
    }
    public void merge(KaryHeap other) {
        //The implementation is as follows:
        //1. Elemente von other.values werden in this.values array reinkopiert
        //2. Dasselbe Ding wie im Konstruktor. Man konstruiert einen neuen Heap,

        int N = indOfLast + 1;
        for(int i = 0; i <= other.indOfLast; i++) {
            indOfLast++;
            this.values[i+N] = other.values[i];
        }
        for(int i = (values.length-1)/dimension; i >= 0; i--){
            if(getChildIndex(0, i) > indOfLast) continue;
            siftDown(i);
        }
    }
    public static void heapSort(int[] array, int dimension) {
        KaryHeap heap = new KaryHeap(array, dimension);
        for(int i = 0; i < array.length; i++){
            heap.extractMax();
        }
        for(int i = 0; i < array.length; i++){
            array[i] = heap.getHeap()[i];
        }
    }
    public static void main(String[] args) {
        //int[] array = new int[]{4, 6, 6, 4};
        int[] array = new int[]{4, 1, 6, 7, 2, 3, 5};
        KaryHeap heap = new KaryHeap(array, 2, 11);
        heap.insert(8);
        heap.insert(0);
        heap.insert(9);
        heap.insert(6);

        heapSort(array, 2);
        while(heap.indOfLast >= 0){
            System.out.println(heap.extractMax());
        }
    }
}
