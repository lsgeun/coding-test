int sequentialIndexSearch(int value[], int size, IndexTable index[], int indexSize, int key) {
    int ret = FAIL;
    int i = 0;
    int start = 0, end = 0;
    
    if (value[0] <= key && key <= value[size-1]) {
        for (i = 0; i < indexSize; i++) {
            if (index[i].key > key) {
                break;
            }
        }
        if (i == indexSize) {
            start = index[i-1].position;
            end = size;
        }
        else {
            start = index[i-1].position;
            end = index[i].position;
        }
        
        ret = sequentialRangeSearchAscendingSorted(value, start, end, key);
    }
    
    return ret;
}

int sequentialRangeSearchAscendingSorted(int value[], int start, int end, int key) {
    int ret = -1;
    int i = 0;
    for (i = start; i < end && value[i] < key; i++) {
        
    }
    
    if (i < end && value[i] == key) {
        ret = i;
    }
    
    return ret;
}

IndexTable* createIndexTable(int value[], int size, int indexSize) {
    IndexTable* pReturn = NULL;
    int i = 0;
    int ratio = 0;
    
    if (size <= 0 || indexSize <= 0) {
        printf("인덱스 크기 및 배열의 크기는 0보다 커야합니다\n");
        return NULL;
    }
    
    ratio = size / indexSize;
    if (size % indexSize > 0) {
        ratio = ratio + 1;
    }
    
    pReturn = (IndexTable*) malloc(sizeof(IndexTable) * indexSize);
    if (pReturn == NULL) {
        printf("오류, 메모리할당 in createIndexTable()\n");
        return NULL;
    }
    
    for (i = 0; i < indexSize; i ++) {
        pReturn[i].position = i * ratio;
        pReturn[i].key = value[i*ratio];
    }
    
    return pReturn;
}

