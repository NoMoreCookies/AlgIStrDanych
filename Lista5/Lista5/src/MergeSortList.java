import core.AbstractSortingAlgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class MergeSortList<T> extends AbstractSortingAlgorithm<T>  {

    public MergeSortList(Comparator<? super T> comparator) {
        super(comparator);
    }

    public LinkedList<T> sort(List<T> ARR) {
        // Zmieniamy na LinkedList zamiast ArrayList
        LinkedList<T> arr = new LinkedList<>(ARR);
        LinkedList<T> temp = new LinkedList<>();

        // Zapełniamy temp nullami, żeby miała taką samą długość jak arr
        for (int i = 0; i < arr.size(); i++) {
            temp.add(null);
        }

        int startIndex = 0;
        int middleIndex;
        int endIndex = arr.size();

        // Iteracyjne łączenie list w pary
        for (int size = 1; size < arr.size(); size = size * 2) {
            while (startIndex < arr.size()) {
                middleIndex = Math.min(startIndex + size, arr.size());
                endIndex = Math.min(startIndex + 2 * size, arr.size());
                merge(arr, temp, startIndex, middleIndex, endIndex);
                startIndex += 2 * size;
            }
            startIndex = 0;
        }

        return arr;
    }

    public void merge(LinkedList<T> arr, LinkedList<T> temp, int left, int mid, int right) {
        int i = left, j = mid, k = left;

        // Iteracyjne scalanie
        while (i < mid && j < right) {
            temp.set(k++, compare(arr.get(i), arr.get(j)) <= 0 ? arr.get(i++) : arr.get(j++));
        }

        // Dodajemy pozostałe elementy z lewej listy
        while (i < mid) {
            temp.set(k++, arr.get(i++));
        }

        // Dodajemy pozostałe elementy z prawej listy
        while (j < right) {
            temp.set(k++, arr.get(j++));
        }

        // Kopiujemy posortowane elementy do oryginalnej listy
        for (int l = left; l < right; l++) {
            arr.set(l, temp.get(l));
        }
    }
}
