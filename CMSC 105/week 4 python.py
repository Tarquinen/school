import math

def computeVolume(r, h):
    """
    Compute the volume of a cylinder with radius r and height h.

    Parameters:
    r (float): radius of the cylinder
    h (float): height of the cylinder

    Returns:
    float: volume of the cylinder
    """
    return math.pi * r**2 * h

def main():
    # Get input from user
    r = float(input("Enter the radius of the cylinder: "))
    h = float(input("Enter the height of the cylinder: "))

    #rerun input if negative value
    while True:       
        if r < 0:
            print('Sorry, negative numbers are not allowed, please enter a new number.') 
            r = float(input("Enter the radius of the cylinder: "))       
        elif h < 0:
            print('Sorry, negative numbers are not allowed, please enter a new number.')
            h = float(input("Enter the height of the cylinder: "))
        else: 
            break

    # Compute volume
    volume = computeVolume(r, h)
        
    # Display result
    print(f"The volume of the cylinder with radius {r} and height {h} is {volume:.2f}.")

# If the script is run directly, then run the main function
if __name__ == "__main__":
    main()