#include <iostream>
#include <vector>
#include <string>
#include <iterator>
#include <random>

using namespace std;

int main()
{
   // double f = 80;
   // std::cout << "Enter the temperature in Fahrenheit ";
   // std::cin >> f;
   // double c = (f - 32) / 1.8;
   // std::cout << f << "f is " << c << "c";

   // std::vector<int> int_vector;
   // std::vector<std::string> string_vector;

   // string_vector.push_back("test");
   // string_vector.push_back("test2");

   // for (string str: string_vector) {
   //    std::cout << str << " ";
   // }

   // std::cout << "\n";

   // std::cout << string_vector.size() << "\n";

   // std::cout << std::endl;
   // std::copy(string_vector.begin(), string_vector.end(), std::ostream_iterator<std::string>(std::cout, " "));

   std::random_device rd;
   std::mt19937 gen(rd());

   std::uniform_int_distribution<> dis(0, 99);
   int random_number;
   std::vector<int> int_list;

   for (int i = 0; i < 100; i++) {
      if (i % 10 == 0 && i != 0)
         std::cout <<"\n";

      random_number = dis(gen);
      int_list.push_back(random_number);

      if (random_number < 10)
         std::cout << random_number << "  ";
      else
         std::cout << random_number << " ";
         
   }
   

   //    std::cout << x << " ";
   // for (int x: int_list) {

   // }

}