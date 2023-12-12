import math
def main():
    print('Daniel Smolsky, CMSC 105, 10/1/23')
    # Get input
    r = float(input('Enter the radius of the cone '))
    h = float(input('Enter the height of the cone '))

    # math of the volume rounded to 2 decimals
    volume = round(computeConeVolume(r, h), 2)
    print('The volume of your cone with radius', r, 'and height', h, 'is:', volume)


def computeConeVolume(r, h):
    """
    Parameters (floats):
    r: radius
    h: height
    """
    return 1 / 3 * math.pi * r ** 2 * h

if __name__ == "__main__":
    main()
