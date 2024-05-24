#include <iostream>

using namespace std;

// problem: https://i.imgur.com/xWCcf5f.png

int main() {
   cout << "Enter a positive integer n: ";
   int n;
   cin >> n;
   if (n < 0) return 0;
   int current_row = 1;
   for (int i = 0; i < n; i++) {
      // print the base of the L, #'s on odd rows and o's on even rows
      for (int j = 0; j < current_row; j++) {
         current_row % 2 == 1 ? cout << "#" : cout << "o";
      }

      // print the alternating o#'s after the backwards L 
      for (int k = current_row; k < n; k++) {
         if (k % 2 == 1) 
            cout << "o";
         else
            cout << "#";
      }

      current_row++;
      cout << endl;
   }
   return 0;
}