import math
import datetime


# This function gets user input for fuel weights of at least 5 aircraft
def fuelInputs():
    fuelArray = [] # this array will store all of the user inputs
    arrayIndex = 0 # this variable is used to modify the elements of the fuelArray
    # Instructions to the user
    print('Enter the fuel loads of every aircraft in the fleet in pounds\n'
        'Press Enter after each fuel load to submit\nEnter "end" when complete')
    
    while True:
        try:
            fuelLoad = input()
            if not (fuelLoad.isdigit() or fuelLoad == 'end'): # reject invalid input
                raise ValueError('Input must be a number or "end"')
            if fuelLoad == 'end' and arrayIndex < 5: # do not allow the user to end without at least 5 items in the array
                raise ValueError('Enter at least 5 values before you end')
            if fuelLoad == 'end' and arrayIndex >= 5: # End user inputs when there are at least 5 values and user has entered 'end'
                break
            fuelArray.append(fuelLoad)
            arrayIndex += 1
        except ValueError as ve:   
            print(ve)

    return fuelArray

# Converts the string pound array to KGs
def poundsToKG(fleetFuelsKGs):

    for i in range(len(fleetFuelsKGs)):
        fleetFuelsKGs[i] = round(int(fleetFuelsKGs[i]) * .45359237, 2) # converts the pound string array to int kg with 2 decimal limit

    return fleetFuelsKGs

# Outputs each value in the array of kg fuel loads
def displayOutput(fleetFuelsKG):
    print('The following are your fuel loads in kilograms:')

    for i in range(len(fleetFuelsKG)):
        print(f'Aircraft {i + 1}: {fleetFuelsKG[i]} kg')

def main():
    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')

    fleetFuelsLBS = fuelInputs() # runs fuelInputs function
    fleetFuelsKGs = poundsToKG(fleetFuelsLBS) # converts the user inputs to KG
    displayOutput(fleetFuelsKGs) # displays the modified array

if __name__ == "__main__":
    main()