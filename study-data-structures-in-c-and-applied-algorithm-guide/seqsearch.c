int seqeuntialSearchNotSorted (int value[], int size, int key) {
    int ret = FAIL;
    int i = 0;
    for (i = 0; i < size && value[i] != key; i++) {
    
    }
    
    if (i < size) {
        ret = i;
    }
    
    return ret;
}

int sequentialSearchAsendingSorted(int value[], int size, int key) {
    int ret = FAIL;
    
    int i = 0;
    for (i = 0; i < size && value[i] < key; i++) {
        
    }
    
    if (i < size && value[i] == key) {
        ret = i;
    }
    
    return ret;
}