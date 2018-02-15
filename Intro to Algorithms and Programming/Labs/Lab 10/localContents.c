#include <stdio.h>
#define STACK_Size 6                

int isEmpty(int top);
int isFull(int top);
int pop(int contents[], int top);
int push(int data, int contents[], int top);

int main() {
   int contents[STACK_Size];     
   int top = 0;  
   // push items on to the contents 
   push(3, contents, top);
   top += 1;
   push(5, contents, top);
   top += 1;
   push(9, contents, top);
   top += 1;
   push(1, contents, top);
   top += 1;
   push(12, contents, top);
   top += 1;
   push(15, contents, top);
   top += 1;

   printf("contents full: %s\n" , isFull(top)?"true":"false");
   printf("contents empty: %s\n" , isEmpty(top)?"true":"false");
   
   printf("Elements: \n");
   printf("Contents at -1 %d\n", contents[-1]);
   // print contents data 
   while(!isEmpty(top)) {
      int data = pop(contents, top);
      printf("Top Val %d\n", top-1);
      top -= 1;
      printf("%d\n",data);
   }

   printf("contents full: %s\n" , isFull(top)?"true":"false");
   printf("contents empty: %s\n" , isEmpty(top)?"true":"false");
   
   return 0;
}

int isEmpty(int top) {

   if(top == 0)
      return 1;
   else
      return 0;
}
   
int isFull(int top) {

   if(top == STACK_Size)
      return 1;
   else
      return 0;
}

int pop(int contents[], int top) {
   int data;
   
   if(!isEmpty(top)) {
     data = contents[top-1];
      return data;
   } else {
      printf("Could not retrieve data, contents is empty.\n");
   }
}

int push(int data, int contents[], int top) {

   if(!isFull(top)) {
      contents[top] = data;
   } else {
      printf("Could not insert data, contents is full.\n");
   }
}
