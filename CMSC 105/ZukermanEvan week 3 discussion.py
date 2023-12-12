#Evan Zukerman, CMSC 105
#09/05/2023

def main():
    print("Type one number")
    valueOne = eval(input())
    print("Type another number")
    valueTwo = eval(input())
    print("Now type one of the fallowing operators to figure out what type of calculation you want to make with the two numbers")
    print("+, -, /, *")
    operatorThing = input()
    if(operatorThing == "+"):
        total = valueOne+valueTwo
        print("So,",valueOne,"+",valueTwo,"=",total)
    elif(operatorThing == "-"):
        subResult =valueOne-valueTwo
        print("So",valueOne,"-",valueTwo,"=",subResult)
    elif(operatorThing == "/"):
        if valueTwo == 0:
            print('cannot divide by 0') # added an edge case where the user tries to divide by 0 (illegal)
        else:
            divisionResult = valueOne/valueTwo
            print("So,",valueOne,"/",valueTwo,"=",divisionResult)
    elif(operatorThing == "*"):
        multiplicationResult = valueOne*valueTwo
        print("So,",valueOne,"*",valueTwo,"=",multiplicationResult)
    else:
        print("you didn't type an acceptable answer.")
main()
    
        
