import math
#for pi I will use math.pi

#prompting for user input of cone measurements

radius = float(input('enter the radius of the cone '))
height = float(input('enter the height of the cone '))

#calculating the volume of the cone

volume = (1/3) * math.pi * radius ** 2 * height

#displaying the volume
print('The volume of your cone is ', volume)
