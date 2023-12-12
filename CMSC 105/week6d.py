import math
import datetime

# Count the amount of 'A's in the input string
def countA(userString, chosenChar):
    totalChosenChar = userString.count(chosenChar.lower()) + userString.count(chosenChar.upper())

    return totalChosenChar

# This function checks if a set of initials occur in the input string in the correct order and of any case combination
def checkInitials(userString):
    initialToSearchFor = 'DS' #variable that stores initials
    j = 0  #variable to check which index of initial to search for

    for i in range(len(userString)):
        if userString[i].lower() == initialToSearchFor[j].lower():
            j += 1
        if j == len(initialToSearchFor):
            return True, initialToSearchFor
    
    return False, initialToSearchFor


def main():
    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')

    # Get a user input string
    userString = str(input('Enter a string: ')) #constraints the variable to string

    # Ask the user to pick which character to search for in the string
    while True:
        try:
            chosenChar = str(input('Enter a single character for the program to search for: '))
            if len(chosenChar) > 1 or not chosenChar.isalpha():
                raise ValueError('Input must be a single alphabet character')
            break
        except ValueError as ve:
            print(ve)

    # Count the amount of 'A's in the string
    totalA = countA(userString, chosenChar)
    print(f'The character {chosenChar} is in your string {totalA} times') #output from countA function
    
    result, initials = checkInitials(userString)

    # prints whether or not initials are found in the string as reported by the checkInitials function
    if result:
        print(f'The initials {initials} were found in the input string')
    else:
        print(f'The initials {initials} were not found in the input string')    

if __name__ == "__main__":
    main()