import core.AbstractSortingAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class MergeSort<T > extends AbstractSortingAlgorithm<T>
{
   
    public MergeSort(Comparator<? super T> comparator){

        super(comparator);
    }

    public ArrayList<T> sort(List<T> ARR)
    {
        ///DEFINIOWANIE POMOCNICZYCH ZMIENNYCH
        //--------------------------------------------------------------------------------------------------------------
        ArrayList<T> arr = (ArrayList<T>)(ARR);
        ArrayList<T> temp = new ArrayList<T>(arr.size());
        int startIndex = 0;
        int middleIndex ;
        int endIndex = arr.size();

        for (int i = 0; i < arr.size(); i++) {
            temp.add(null); // Initialize temp with nulls
        }
        //--------------------------------------------------------------------------------------------------------------

        ///Musimy wykonać tyle razy, no ogl żeby przejść przez te wszystkie długości (1,2,4,8.....)
        //--------------------------------------------------------------------------------------------------------------
        for(int size = 1; size < arr.size();size = size *2)
        {
            ///Tutaj jak mamy długość ogarniętą , to przechodzimy przez wszystkie przedziały tej długości i łączymy je dwujkami, zaczynając najpierw od wszystkich przedziałów długości 1 i łącząc je w dwójki
            //--------------------------------------------------------------------------------------------------------------
            while(startIndex < arr.size())
            {
                middleIndex = Math.min(startIndex+size,arr.size());

                endIndex = Math.min(startIndex+2*size,arr.size());

                merge(arr,temp,startIndex,middleIndex,endIndex);

                startIndex+=2*size;
            }
            startIndex = 0;
            //--------------------------------------------------------------------------------------------------------------
        }
        //--------------------------------------------------------------------------------------------------------------
        return arr;
    }

    public void merge(ArrayList<T> arr,ArrayList<T > temp  ,int left, int mid ,int right)
    {
        ///ZM.Pomocnicze
        //--------------------------------------------------------------------------------------------------------------
        int i = left, j = mid, k = left;
        //--------------------------------------------------------------------------------------------------------------

        /// W pomocniczej tablicy ustawiam po kolei mergując z dwóch poprzednich
        //--------------------------------------------------------------------------------------------------------------
        while(i < mid && j < right)
        {
            temp.set((k++),(compare(arr.get(i),(arr.get(j))) <= 0)? arr.get(i++):arr.get(j++));
        }
        //--------------------------------------------------------------------------------------------------------------

        /// jeżeli coś zostało w lewej tablicy to dodaję to na koniec naszej tablicy
        //--------------------------------------------------------------------------------------------------------------
        while (i < mid) {
            temp.set(k++, arr.get(i++));
        }
        //--------------------------------------------------------------------------------------------------------------

        /// jeżeli coś zostało w prawej tablicy to dodaję to na koniec naszej tablicy
        //--------------------------------------------------------------------------------------------------------------
        while (j < right) {
            temp.set(k++, arr.get(j++));
        }
        //--------------------------------------------------------------------------------------------------------------

        ///Z mergowaną tablicę ustawiam w naszej tablicy
        //--------------------------------------------------------------------------------------------------------------
        for (int l = left; l < right; l++) {
            arr.set(l, temp.get(l));
        }
        //--------------------------------------------------------------------------------------------------------------
    }
}
