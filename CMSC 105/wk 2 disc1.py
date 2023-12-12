import math


#prompting for user input

radius = float(input("enter radius: "))
height = float(input("enter height: "))

#calculating the volume

volume = math.pi *radius ** 2 * height

#display the result
print("the volume is:", round(volume, 2), 'cubic units')

#calculate volume of a sphere with the same radius
volSphere = (4/3) * math.pi * radius ** 3

#calculate how many spheres you can fit into the cylinder
sphereCount = height // (radius * 2)

#calculate leftover space
leftover = volume - sphereCount * volSphere

if (volSphere > volume):
    print('You cannot fit any spheres into this cylinder with the same radius as the cylinder')
else:
    print('You can fit', math.trunc(sphereCount), 'spheres with the same radius into your cylinder with', round(leftover, 2), 'cubic units room remaining')

