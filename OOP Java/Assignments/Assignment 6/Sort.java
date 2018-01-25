public class Sort {
    public static void sortAnything(Sortable listObjects[],  int numObjects){
        Sortable temp;
        int indexSmallest, index1, index2;
        for (index1 = 0; index1 < numObjects - 1; index1++){
            indexSmallest = index1;
            for (index2 = index1 + 1; index2 < numObjects; index2++)
                if (listObjects[index2].lessThan(listObjects[indexSmallest]))
                    indexSmallest = index2;
            temp = listObjects[index1];
            listObjects[index1]  = listObjects[indexSmallest];
            listObjects[indexSmallest] = temp;
        }
    }     
}