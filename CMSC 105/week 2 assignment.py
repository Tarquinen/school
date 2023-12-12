import math
#user input
print('Daniel Smolsky, CMSC 105, 9/23/23')
print(4 ** 1 ** 2)
delivPerDay = int(input('Enter how many papers are delivered on the route per day: '))
delivPerWeek = int(input('Enter how many days the paper is delivered per week: '))
tips = float(input('Enter total tips for the week : '))

newsCost = 3.5 #cost per newspaper
commis = .05 #.05 represents 5% commmission rate
salary = delivPerDay * delivPerWeek * newsCost * commis #weekly salary
totalPay = salary + tips #total pay including tips

print('The total amount of newspaper deliveries per week is', math.trunc(delivPerDay * delivPerWeek))
print('The weekly salary is', salary, 'dollars')
print('The total pay for the week is', totalPay, 'dollars')

