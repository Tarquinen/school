#include <iostream>
#include <vector>
#include <string>
#include <iterator>
#include <random>

using namespace std;

void printIntList(const vector<int>& int_list) {
   for (int i = 0; i < int_list.size(); i++) {
      if (int_list[i] < 10) {
         cout << int_list[i] << "  ";
      } else {
         cout << int_list[i] << " ";
      }
      
      if ((i + 1) % 10 == 0) {
         cout << endl;
      }
   }
   cout << endl;
}

vector<int> selectionSort(const vector<int>& int_list) {
   vector<int> selection_sorted_list = int_list;
   int smallest;
   int temp;
   for (int i = 0; i < selection_sorted_list.size() - 1; i++) {
      smallest = i;
      for (int j = i + 1; j < selection_sorted_list.size(); j++) {
         if (selection_sorted_list[j] < selection_sorted_list[smallest]) {
            smallest = j;
         }
      }
      temp = selection_sorted_list[i];
      selection_sorted_list[i] = selection_sorted_list[smallest];
      selection_sorted_list[smallest] = temp;
   }
   return selection_sorted_list;
}


int main() {
   random_device rd;
   mt19937 gen(rd());

   uniform_int_distribution<> dis(0, 99);
   int random_number;
   vector<int> int_list;
   int amount_to_generate = 20;

   for (int i = 0; i < amount_to_generate; i++) {
      random_number = dis(gen);
      int_list.push_back(random_number);
   }
   printIntList(int_list);
   cout << "Selection Sort" << endl;
   cout << endl;
   vector<int> selection_sorted_list = selectionSort(int_list);
   printIntList(selection_sorted_list);
}