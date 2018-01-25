#include <stdio.h>

void bubblesort(int a[], int size);
void selectsort(int a[], int size);


int main(void){
	int a[] = {1,2,6,4,8,7,91,0};

	for (int i=0; i<8;i++){
		printf("%d ", a[i]);
	}
	printf("\n");
	selectsort(a,8);
	for (int i=0; i<8;i++){
		printf("%d ", a[i]);
	}




	return 0;
}


void bubblesort(int a[], int size){
	for(int i = 0; i < size-1; i++){
		for(int j = 0; j < size-1 ;j++){
			if (a[j] > a[j+1]){
				int temp = a[j+1];
				a[j+1] = a[j];
				a[j] = temp;
			}
		}
	}
	return ;
}

void selectsort(int a[], int size){
	int *point;
	for (int i=0;i < size-1;i++){
		point = &a[0];
		for (int j=0;j< size-i;j++){
			if (*point < a[j]){
				point = &a[j];
			}
		}
		int temp = a[size-i-1];
		a[size-i-1] = *point;
		*point = temp;
	}
	return;
}