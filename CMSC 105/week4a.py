import math
import datetime
import sys

def getInput(service, typeCleaningStr, options):


    if service == 'house':
        while True:
            typeCleaning = input(typeCleaningStr)
            cleaningArray = typeCleaning.split(',') # Convert the input string into an array
            fixedArray = [item.strip() for item in cleaningArray]  # Remove leading and trailing spaces
            uniqueArray = list(set(fixedArray)) # Remove duplicate values from the array
            
            print('How many rooms are in your house:')
            while True:
                try:
                    numRooms = int(input()) # constrained to positive integer values only, this variable represents the amount of rooms requested for the cleaning.
                    if numRooms < 0:
                        raise ValueError()
                    break
                except ValueError as ve:
                        print('Please enter a positive interger')
                
            # Check if all entered services are valid
            if all(item in options for item in uniqueArray):
                return numRooms, uniqueArray
            else:
                invalid_services = [item for item in uniqueArray if item not in options]
                print('You have entered invalid services:', ', '.join(invalid_services))

def houseCleaning():

    # Array of options created to easily add more cleaning options in the future if desired
    options = ['floors', 'windows', 'bathrooms', 'dusting']  # possible options for the customer to choose
    optionsString = ', '.join(options)  # converts array into string
    typeCleaningStr = 'What items do you want cleaned? Enter as many as you want separated by commas (e.g. ' + optionsString + ') ' # Used in getInput function to display the available options

    numRooms, services = getInput('house', typeCleaningStr, options) # Input desired services and check that they match available options, if not ask for new inputs

    cost = costCalc(numRooms, services) # calculate the cost based on the amount of rooms and services requested
    print('The total cost of the service is', cost, 'dollars')



def yardService():
    








def costCalc(numRooms, services):

    cost = 30 # This variable stores the value of the service with an initial base cost.

    if numRooms <= 3: #small number of rooms (less than 3)
        for item in services: # loop to add to the cost if the array services has any of the following choices
            if item == 'floors':
                cost = cost + 20
            elif item == 'windows':
                cost = cost + 10
            if item == 'bathrooms':
                cost = cost + 40
            if item == 'dusting':
                cost = cost + 15
                
    if 3 < numRooms <= 5: #medium number of rooms (less than 5)
        for item in services:
            if item == 'floors':
                cost = cost + 30
            elif item == 'windows':
                cost = cost + 20
            if item == 'bathrooms':
                cost = cost + 60
            if item == 'dusting':
                cost = cost + 25

    if 5 < numRooms: #large number of rooms (more than 5), scales for very large homes (more than 8)
        for item in services:
            if item == 'floors':
                cost = cost + 40
                if numRooms > 8:
                   cost = cost + ((numRooms - 8) * 5)
            elif item == 'windows':
                cost = cost + 30
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 3)
            if item == 'bathrooms':
                cost = cost + 80
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 5)
            if item == 'dusting':
                cost = cost + 35
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 5)

    return cost

def main():
    print('Daniel Smolsky,', datetime.date.today(), end=', CMSC 105\n')


    print('Hello! are you looking for a house cleaning or yard service?\n'
          'Enter "house" for house cleaning or "yard" for yard cleaning:')

    while True:
        try:
            serviceType = input()
            if serviceType != 'house' and serviceType != 'yard':
                raise ValueError()
            break
        except ValueError as ve:
            print('Please enter "house" or "yard"')
        print(serviceType)

    if serviceType == 'house':
        houseCleaning()
    else:
        yardService()

   
if __name__ == "__main__":
    main()