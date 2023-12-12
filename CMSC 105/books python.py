def main():
    """
    This program calculates the total number of books read by the user in a month.
    The user keeps entering the number of books read each day until they enter a sentinel value of -1.
    """
    
    total_books = 0  # Initialize the counter for total number of books
    countInputs = 0 
    
    print("Enter the number of books you read each day. When you're done, enter -1 to stop.")
    
    while True:  # Start an infinite loop
        try:
            books_today = int(input("Enter number of books read today: "))  # Prompt the user for input
            
            # Check for the sentinel value
            if books_today == -1:
                break
            
            # Check for negative input (other than the sentinel value)
            if books_today < 0:
                print("Please enter a positive number or -1 to stop.")
                continue
            
            total_books += books_today  # Add the number of books read today to the total
            countInputs += 1
            averageBooks = total_books / countInputs

        except ValueError:  # Handle the case where user doesn't enter a valid number
            print("Please enter a valid number or -1 to stop.")
    
    # Display the result
    print(f"\nAverage number of books read this month: {averageBooks}")

if __name__ == "__main__":
    main()