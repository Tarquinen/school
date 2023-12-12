#inputs

input1 = float(input('Input a number '))
input2 = float(input('Input another number '))
operator = input('enter an operator (e.g. +, -, *, /) ')

check = ['+', '-', '*', '/']
while True:
    if operator in check:
        break
    else:
        print('You have entered an invalid operator, try again')
        operator = input('enter an operator (e.g. +, -, *, /) ')

if operator == '+':
    print(input1 + input2)
    
elif operator == '-':
    print(input1 - input2)

elif operator == '*':
    print(input1 * input2)

elif operator == '/':
    print(input1 / input2)
