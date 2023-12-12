import math
import datetime

# This function will ask the user to input the amount of people on the trip, trip duration, and prices of food/gas on each day
def userInputs():
    numPeople = 0 # int variable to store # of people on the trip
    numDays = 0 # int varialbe to store duration of trip in days, will also represent the number of elements in the arrays below
    food = [] # array to store cost of food on each day
    gas = [] # array to store cost of gas on each day
    i = 0 # array index

    # Get input from user for number of people on the trip, reject invalid numbers
    print('Enter the number of people on the trip:')
    while True:
        try:
            numPeople = int(input())
            if numPeople < 0: # can't have negative people
                    raise ValueError()
            break
        except ValueError as ve:
            print('please enter a positive number')
        

    # Get input from user for number of days of the trip, reject invalid numbers
    print('Enter the number of days of the trip:')
    while True:
        try:
            numDays = int(input())
            if numDays < 0: # can't have negative days
                    raise ValueError()
            break
        except ValueError as ve:
            print('please enter a positive number')

    food = [None] * numDays # initializes numDays amount of elements in these arrays so the loop below can store to them without using append
    gas = [None] * numDays

    # Get input from user for cost of food and gas on each day then store to the appropriate array the same amount of times as total days of the trip
    print('Enter the total food and gas price for each day:')
    while i < numDays:
        try:
            food[i] = (float(input(f'Day {i + 1} food: '))) # has to store input to a specific element instead of using append because if the loop catches an exception gas and food might have a different amount of elements
            gas[i] = (float(input(f'Day {i + 1} gas: ')))
            if food[i] < 0 or gas[i] < 0: # don't accept negative values
                    raise ValueError()
            i += 1
        except ValueError as ve:
            print('please enter a positive number')

    return numPeople, numDays, food, gas

def main():
    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')

    currency = input('Enter the currency name of the country visited: ') # documents the currency of the country visited

    numPeople, numDays, food, gas = userInputs() #stores the return from userInputs to 4 different variables
    totalCostsGas = sum(gas) # stores the value of the sum of all values in the gas costs array
    totalCostsFood = sum(food) # stores the value of the sum of all values in the food costs array
    totalTripCost = totalCostsGas + totalCostsFood # total cost of the trip (food+gas)
    eachShare = round(totalTripCost / numPeople, 2) # individual costs rounded to cents

    # Print the outputs from the calculations above
    print(f'The total cost of the food was: {totalCostsFood} {currency}')
    print(f'The total cost of the gas was: {totalCostsGas} {currency}')
    print(f'The total cost of the trip was: {totalTripCost} {currency}')
    print(f'Each person\'s share of the total cost was: {eachShare} {currency}')


if __name__ == "__main__":
    main()