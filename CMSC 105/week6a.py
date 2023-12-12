import math
import datetime

# This function checks to see the password is between 6 and 15 length long
def checkLength(password):
    if len(password) >= 6 and len(password) <= 15:
        return True # returns True if the password is the correct length
    else:
        print('The length of your password is incorrect\nEnter a new password:')
        return False

# This functions checks to see if there are spaces in the password
def checkBlanks(password):
    if password.find(' ') == -1:
        return True # returns True if it does not find any spaces
    else:
        print('The password contains a space\nEnter a new password:')
        return False

# This function checks to see if the input parameter contains both digits and characters
def digitAndCharacter(password):
    charCount = 0 # counter for characters in the password
    digitCount = 0 # counter for digits in the password

    for i in password:
        if i.isalpha():
            charCount += 1
        elif i.isdigit():
            digitCount += 1

    if charCount > 0 and digitCount > 0:
        return True # returns true when there is more than 0 digits and characters
    elif charCount == 0:
        print('The password does not contain a character\nEnter a new password:')
        return False # otherwise returns false and prints to the user what specifically is missing
    elif digitCount == 0:
        print('The password does not contain a digit\nEnter a new password:')
        return False

def main():

    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')
    # Instruction to the user on password requirements
    print('Enter a password that fits the following criteria:\n6-15 characters\nDoes not include any space\nContains at least one digit and one character')

    # Loop so the user is requested to enter a new password until a valid one is created
    while True:
        password = input() # This variable stores the password that the user input
        if checkLength(password) and checkBlanks(password) and digitAndCharacter(password): # if all of the functions evaluate to True the password is valid and the loop breaks
            print('Your password is valid')
            break

if __name__ == "__main__":
    main()