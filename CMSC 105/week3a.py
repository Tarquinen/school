def main():
    print('Daniel Smolsky, CMSC 105, 9/24/23')
    import sys

    #array of options created to easily add more options in the future if desired
    options = ['floors', 'windows', 'bathrooms', 'dusting'] #possible options for customer to choose
    optionsString = ', '.join(options) #converts array into string
    typeCleaningStr = 'What items do you want cleaned? Enter as many as you want \nseperated by commas(e.g., ' + optionsString + ') ' #used later in typeCleaning

    #user inputs
    numRooms = int(input('How many rooms are in your house? '))
    typeCleaning = input(typeCleaningStr)



    #convert typeCleaning into an array and remove spaces and remove duplicates
    cleaningArray = typeCleaning.split(',')
    fixedArray = [item.replace(' ', '') for item in cleaningArray]
    uniqueArray = list(set(fixedArray))

    #Checks that the customer hasn't entered any invalid cleaning options
    for item in uniqueArray:
         if item in options:
             pass
         else:
            print('You have entered an invalid service:', item,)
            sys.exit(1) #terminates the script

    #calculate costs
    cost = 30 #base cleaning fee

    if numRooms <= 3: #small number of rooms (less than 3)
        for item in uniqueArray:
            if item == 'floors':
                cost = cost + 20
            elif item == 'windows':
                cost = cost + 10
            if item == 'bathrooms':
                cost = cost + 40
            if item == 'dusting':
                cost = cost + 15
                
    if 3 < numRooms <= 5: #medium number of rooms (less than 5)
        for item in uniqueArray:
            if item == 'floors':
                cost = cost + 30
            elif item == 'windows':
                cost = cost + 20
            if item == 'bathrooms':
                cost = cost + 60
            if item == 'dusting':
                cost = cost + 25

    if 5 < numRooms: #large number of rooms (more than 5), scales for very large homes (more than 8)
        for item in uniqueArray:
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


    print('The total cost of the service is', cost, 'dollars')
if __name__ == "__main__":
    main()

            

