import math
import datetime
import sys

def main():
    print('Daniel Smolsky,', datetime.date.today(), end=', CMSC 105\n')

    print('Hello! are you looking for a house cleaning or yard service?\n'
          'Enter "house" for house cleaning or "yard" for yard cleaning:')

    # ask user if they want a house or yard service
    while True:
        try:
            serviceType = input()
            if serviceType != 'house' and serviceType != 'yard': # only accept "house" or "yard" input
                raise ValueError()
            break
        except ValueError as ve:
            print('Please enter "house" or "yard"')

    # run houseCleaning if serviceType is "house", else if serviceType is "yard" run yardService
    if serviceType == 'house':
        houseCleaning()
    elif serviceType == 'yard':
        yardService()


# this function runs the function for input related to house cleaning, runs the cost calculation and runs the senior discount function
def houseCleaning():

    # Array of options created to easily add more cleaning options in the future if desired
    options = ['floors', 'windows', 'bathrooms', 'dusting']  # possible options for the customer to choose
    optionsString = ', '.join(options)  # converts array into string
    typeCleaningStr = 'What items do you want cleaned? Enter as many as you want separated by commas \n(e.g. ' + optionsString + '): \n' # Used in getInput function to display the available options

    services, numRooms = getInput('house', typeCleaningStr, options) # store user inputs from getInput function into numRooms and services variables
    
    servicesString = ', '.join(services) # converts services array into a string for later printing
    cost = houseCostCalc(services, numRooms) # calculate the cost based on the amount of rooms and services requested
    discount('house', servicesString, cost) #apply senior discount to the cost


# this function runs the function for input related to yard cleaning, runs the cost calculation and runs the senior discount function
def yardService():

    # Array of options created to easily add more yard services in the future if desired
    options = ['mowing', 'edging', 'shrub pruning']  # possible options for the customer to choose
    optionsString = ', '.join(options)  # converts array into string
    typeCleaningStr = 'What do you want serviced in your yard? Enter as many as you want separated by commas \n(e.g. ' + optionsString + '): \n' # Used in getInput function to display the available options

    services, yardArea, yardPerimeter, shrubCount = getInput('yard', typeCleaningStr, options) # store user inputs from getInput function into numRooms and services variables
   
    servicesString = ', '.join(services) # converts services array into a string for later printing
    cost = yardCostCalc(services, yardArea, yardPerimeter, shrubCount) # calculate the cost based on the amount of rooms and services requested
    discount('yard', servicesString, cost) #apply senior discount to the cost


# this functions gets all user input required to calculate costs for services
def getInput(service, typeCleaningStr, options):

    # Get all the relevant user input if they ask for a house service
    if service == 'house':
        while True:
            # get user input for types of cleaning they want
            typeCleaning = input(typeCleaningStr)
            cleaningArray = typeCleaning.split(',') # Convert the input string into an array
            fixedArray = [item.strip() for item in cleaningArray]  # Remove leading and trailing spaces
            uniqueArray = list(set(fixedArray)) # Remove duplicate values from the array
            
            # Check if all entered services are in the options list
            if all(item in options for item in uniqueArray):
                break
            else:
                invalid_services = [item for item in uniqueArray if item not in options]
                print('You have entered invalid services:', ', '.join(invalid_services))

        print('How many rooms are in your house:')
        while True:
            try:
                numRooms = int(input()) # constrained to positive integer values only, this variable represents the amount of rooms requested for the cleaning.
                if numRooms < 0:
                    raise ValueError()
                break
            except ValueError as ve:
                    print('Please enter a positive integer')
        return uniqueArray, numRooms
    

    # Get all the relevant user input if they ask for a yard service            
    elif service == 'yard':
        yardShape = None # yes if the yard is square/rectangle, no if not
        yardPerimeter = None # perimeter of the yard in feet for the edging service
        yardArea = None # area of the yard in feet for the mowing service
        shrubCount = None # amount of shrubs in the yard
        

        while True:
            # get user input for types of cleaning they want
            typeCleaning = input(typeCleaningStr)
            cleaningArray = typeCleaning.split(',') # Convert the input string into an array
            fixedArray = [item.strip() for item in cleaningArray]  # Remove leading and trailing spaces
            uniqueArray = list(set(fixedArray)) # Remove duplicate values from the array

            # Check if all entered services are valid
            if all(item in options for item in uniqueArray):
                break
            else:
                invalid_services = [item for item in uniqueArray if item not in options]
                print('You have entered invalid services:', ', '.join(invalid_services))
        
        # ask user for yard area/perimeter specifics if they want mowing or edging
        if 'mowing' in uniqueArray or 'edging' in uniqueArray:
            # ask user if yard is square/rectangle to calculate area and perimeter
            print('Is your yard a square or rectangle? Enter "yes" or "no":')
            while True:
                try:
                    yardShape = input()
                    if yardShape != 'yes' and yardShape != 'no': # reject invalid inputs
                        raise ValueError()
                    break
                except ValueError as ve:
                    print('Please enter "yes" or "no"')

            if yardShape == 'yes': # yard is square or rectangle
                print('Enter the dimensions of your yard in feet (X x Y):')
                while True:
                    try:
                        xYard = float(input('X: '))
                        yYard = float(input('Y: '))
                        if xYard < 0 or yYard < 0: # reject invalid inputs
                            raise ValueError()
                        break
                    except ValueError as ve:
                        print('Enter a positive number')
                yardArea = xYard * yYard
                yardPerimeter = xYard * 2 + yYard * 2

            elif yardShape == 'no': # yard shape is weird
                print('Enter the area and perimeter of your yard:') # get the area and perimeter of the yard
                while True:
                    try:
                        yardArea = float(input('Area in square feet: '))
                        yardPerimeter = float(input('Perimeter in feet: '))
                        if yardArea < 0 or yardPerimeter < 0: # reject invalid inputs
                            raise ValueError()
                        break
                    except ValueError as ve:
                        print('Enter a positive number')

        # ask about shrubs if its a service they requested    
        if 'shrub pruning' in uniqueArray:    
            print('How many shrubs are in your yard?')
            while True:
                try:
                    shrubCount = int(input())
                    if shrubCount < 0: # reject invalid inputs
                        raise ValueError()
                    break
                except ValueError as ve:
                    print('Enter a positive integer')

        return uniqueArray, yardArea, yardPerimeter, shrubCount


# this function evaluates costs for house services
def houseCostCalc(services, numRooms):
    cost = 30 # This variable stores the value of the service with an initial base cost.
    if numRooms <= 3: #small number of rooms (less than 3)
        for item in services: # loop to add to the cost if the array services has any of the following choices
            if item == 'floors':
                cost = cost + 20 #floors cost $20
            elif item == 'windows':
                cost = cost + 10 #windows cost $10
            elif item == 'bathrooms':
                cost = cost + 40 #bathrooms cost $40
            elif item == 'dusting':
                cost = cost + 15 #dusting cost $15
                
    if 3 < numRooms <= 5: #medium number of rooms (less than 5)
        for item in services:
            if item == 'floors': 
                cost = cost + 30 #floors cost $30
            elif item == 'windows':
                cost = cost + 20 #windows cost $20
            elif item == 'bathrooms':
                cost = cost + 60 #bathrooms cost $60
            elif item == 'dusting':
                cost = cost + 25 #dusting cost $25

    if 5 < numRooms: #large number of rooms (more than 5), scales for very large homes (more than 8)
        for item in services:
            if item == 'floors':
                cost = cost + 40 #floors cost $40
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 5) # 5 additional dollars for every room past 8
            elif item == 'windows':
                cost = cost + 30 #windows cost $30
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 3) # 3 additional dollars for every room past 8
            elif item == 'bathrooms':
                cost = cost + 80 #bathrooms cost $80
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 10) # 10 additional dollars for every room past 8
            elif item == 'dusting':
                cost = cost + 35 #dusting cost $35
                if numRooms > 8:
                    cost = cost + ((numRooms - 8) * 4) # 4 additional dollars for every room past 8

    return cost

# this function evaluates costs for yard services
def yardCostCalc(services, yardArea, yardPerimeter, shrubCount):
    cost = 50 # This variable stores the value of the service with an initial base cost.
    for item in services:
        if item == 'mowing': # mowing costs 80c per square foot
            cost = cost + yardArea * .8
        elif item == 'edging':
            cost = cost + yardPerimeter * 1.4 # edging costs $1.40 per foot
        elif item == 'shrub pruning':
            cost = cost + shrubCount * 4 # shrub pruning costs $4 per shrub

    return cost


# This function applies the senior discount to the total cost of the service
def discount(serviceType, servicesString, cost):
    print(f'Is this customer a senior citizen? ("yes" or "no")')
    while True:
        try:
            senior = input()
            if senior!= 'yes' and senior!= 'no': # only accept "yes" and "no" input
                raise ValueError()
            break
        except ValueError as ve:
            print('Please enter "yes" or "no"')

    if senior == 'yes':
        cost = cost - (cost * 0.15) # apply 15% senior discount to the cost

    print(f'The total cost of the {servicesString} {serviceType} service is {cost} dollars') #output the final cost of the service

if __name__ == "__main__":
    main()