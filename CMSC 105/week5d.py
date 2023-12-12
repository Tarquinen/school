import math
import datetime

def main():
    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')
    hoursWorked = 0 # initiliaze a variable to track hours worked

    while True:
        # get user input
        inputStr = input('How many hours did you work today? ')

        if inputStr == 'end': # break out of the loop if the user inputs 'end'
            break
        else: #add the user input to the hoursWorked variable and display the total sum so far
            inputFloat = float(inputStr)
            hoursWorked += inputFloat
            print('total work hours:', hoursWorked) # display the total hours worked

if __name__ == "__main__":
    main()