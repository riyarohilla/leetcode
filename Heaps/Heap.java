import java.util.ArrayList;

class Heap<T extends Comparable<T>> {
  private List<T> list;
  public Heap() {
    list = new ArrayList<>();
  }

  private void swap(int first, int second) {
    T temp = list.get(first);
    list.set(first, list.get(second));
    list.set(second, temp);
  }

  private int parent(int index) {
    return (index - 1) / 2; // 0th index otherwise index/2
  }

  private int left(int index) {
    return 2 * index + 1; // 0th index otherwise 2 * index
  }

  private int right(int index) {
    return 2 * index + 2; // 0th index otherwise 2 * index + 1
  }

  private void insert(T value) {
    list.add(value);
    upheap(list.size() - 1);
  }

  private void upheap(int index) {
    if(index == 0) {
      return;
    }

    int p = parent(index);
    if(list.get(index).compareTo(list.get(p)) < 0) {
      swap(index, p);
      upheap(p);
    }
  }

  private T remove() throws Exception {
    if(list.isEmpty()) {
      throw new Exception("Heap is empyty, can't remove");
    }

    T temp = list.get(0);
    T last = list.remove(list.size() - 1);

    if(!list.isEmpty()) {
      list.set(0, last);
      downheap(0);
    }
    return temp;
  }

  private void downheap(int index) {
    int min = index;
    int l = left(index);
    int r = right(index);

    if(l < list.size() && list.get(min).compareTo(list.get(l)) > 0) {
      min = l;
    }

    if(r < list.size() && list.get(min).compareTo(list.get(r)) > 0) {
      min = r;
    }

    if(min != index) {
      swap(min, index);
      downheap(min);
    }
  }

  public ArrayList<T> heapSort() throws Exception {
    ArrayList<T> data = new ArrayList<>();
    while(!list.isEmpty()) {
      data.add(this.remove());
    }
    return data;
  }
}
