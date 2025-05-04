import core.AbstractSortingAlgorithm;

import java.util.*;

public class QuickSort<T> extends AbstractSortingAlgorithm<T> implements QuickSortInterface<T>{

    ///TWORZĘ ZMIENNE POMOCNICZE
    //------------------------------------------------------------------------------------------------------------------
    private final boolean useRandomPivot;
    private final Random random = new Random();
    //------------------------------------------------------------------------------------------------------------------


    /// Konstruktor
    //------------------------------------------------------------------------------------------------------------------
    public QuickSort(Comparator<? super T> comparator, boolean useRandomPivot) {
        super(comparator);
        this.useRandomPivot = useRandomPivot;
    }
    //------------------------------------------------------------------------------------------------------------------

    /// Główna metoda sortująca
    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<T> sort(List<T> arr) {
        ArrayList<T> ARR = new ArrayList<>(arr);
        quickSort(ARR, 0, arr.size() - 1);
        return ARR ;
    }
    //------------------------------------------------------------------------------------------------------------------

    /// REKURENCYJNA FUNKCJA QUICKSORT(DZIELENIE NA PRAWO I LEWO
    //------------------------------------------------------------------------------------------------------------------
    private void quickSort(ArrayList<T> arr, int left, int right) {
        if (left < right)
        {
            int pivotIndex = partition(arr, left, right);

            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    /// FUNKCJA DZIELĄCA TABLICĘ(TEN KOD ODPOWIADA ZA PRZEŻUCANEI ELEMENTÓW TABLICY)
    //------------------------------------------------------------------------------------------------------------------
    private int partition(ArrayList<T> arr, int left, int right)
    {

        int pivotIndex = choosePivotIndex(left, right);
        T pivotValue = arr.get(pivotIndex);

        Collections.swap(arr, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (compare(arr.get(i), pivotValue) < 0) {
                Collections.swap(arr, i, storeIndex);
                storeIndex++;
            }
        }

        Collections.swap(arr, storeIndex, right); // wstawiamy pivot na właściwe miejsce

        return storeIndex;
    }
    //------------------------------------------------------------------------------------------------------------------

    /// WYBÓR INDEXU PIVOTA: losowy lub pierwszy
    //------------------------------------------------------------------------------------------------------------------
    private int choosePivotIndex(int left, int right) {
        return useRandomPivot
                ? left + random.nextInt(right - left + 1)
                : left;
    }
    //------------------------------------------------------------------------------------------------------------------
}
