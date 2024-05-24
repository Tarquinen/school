#include <iostream>
#include <vector>
#include <string>
#include <iterator>
#include <random>
#include <chrono>
#include <cmath>

using namespace std;

void printIntList(const vector<int>& int_list) {
   int max = 0;
   for (int x: int_list) if (x > max) max = x;
   int num_digits = log10(max) + 1;

   // formatting for better printing
   for (int i = 0; i < int_list.size(); i++) {
      for (int j = 0; j < num_digits - log10(int_list[i]); j++) {
         cout << " ";
      }
      cout << int_list[i];
      
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

vector<int> merge(const vector<int>& left, const vector<int>& right) {
   vector<int> merged;
   int left_index = 0, right_index = 0;

   while (left_index < left.size() && right_index < right.size()) {
      if (left[left_index] < right[right_index]) {
         merged.push_back(left[left_index++]);
      }
      else {
         merged.push_back(right[right_index++]);
      }
   }

   while (left_index < left.size()) {
      merged.push_back(left[left_index++]);
   }
   while (right_index < right.size()) {
      merged.push_back(right[right_index++]);
   }

   return merged;
}

vector<int> mergeSort(const vector<int>& int_list) {
   if (int_list.size() <= 1) return int_list;

   int mid = int_list.size() / 2;

   vector<int> left_half(int_list.begin(), int_list.begin() + mid);
   vector<int> right_half(int_list.begin() + mid, int_list.end());
   
   left_half = mergeSort(left_half);
   right_half = mergeSort(right_half);

   return merge(left_half, right_half);
}

vector<int> bucketSort(const vector<int>& int_list) {
   vector<int> sorted_list = int_list;
   int max { 0 };
   for (int x: int_list) if (x > max) max = x;
   // cout << max << "\n";

   int num_digits = log10(max) + 1;
   // cout << num_digits << " ";

   vector<vector<int>> buckets_vector(10);

   for (int i = 0; i < num_digits; i++) {
      for (int j = 0; j < sorted_list.size(); j++ ) {
         int place_value = (sorted_list[j] / static_cast<int>(pow(10, i))) % 10;
         buckets_vector[place_value].push_back(sorted_list[j]);
      }

      sorted_list.clear();
      for (auto& bucket: buckets_vector) {
         for (int x: bucket) {
            sorted_list.push_back(x);
         }
         bucket.clear();
      }
   }

   return sorted_list;
}

int main() {
   random_device rd;
   mt19937 gen(rd());

   uniform_int_distribution<> dis(0, 1000);
   int random_number;
   vector<int> int_list;
   int amount_to_generate = 10000;

   for (int i = 0; i < amount_to_generate; i++) {
      random_number = dis(gen);
      int_list.push_back(random_number);
   }
   // printIntList(int_list);

   // initialising variables for timing
   auto start = chrono::high_resolution_clock::now();
   auto end = start;
   auto duration = chrono::duration_cast<chrono::milliseconds>(end - start).count(); 
   
   cout << "Selection Sort" << endl;
   start = chrono::high_resolution_clock::now();
   vector<int> selection_sorted_list = selectionSort(int_list);
   end = chrono::high_resolution_clock::now();
   duration = chrono::duration_cast<chrono::milliseconds>(end - start).count();
   cout << "Time taken: " << duration << " milliseconds" << endl;
   // printIntList(selection_sorted_list);

   cout << "Merge Sort" << endl;
   start = chrono::high_resolution_clock::now();
   vector<int> merge_sorted_list = mergeSort(int_list);
   end = chrono::high_resolution_clock::now();
   duration = chrono::duration_cast<chrono::milliseconds>(end - start).count();
   cout << "Time taken: " << duration << " milliseconds" << endl;
   // printIntList(merge_sorted_list);
   
   cout << "Bucket radix Sort" << endl;
   start = chrono::high_resolution_clock::now();
   vector<int> bucket_sorted_list = bucketSort(int_list);
   end = chrono::high_resolution_clock::now();
   duration = chrono::duration_cast<chrono::milliseconds>(end - start).count();
   cout << "Time taken: " << duration << " milliseconds" << endl;
   // printIntList(bucket_sorted_list);
}