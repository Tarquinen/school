def count_syllables(word):
    vowels = "AEIOUaeiou"
    count = 0
    prev_char = ""

    for char in word:
        if char in vowels and prev_char not in vowels:
            count += 1
        prev_char = char

    return count

def digitsInName(last_name):
    return len(last_name)

def analyze_last_name(last_name, initials):
    # Task 1: Counting syllables in the last name
    syllable_count = count_syllables(last_name)

    # Task 2: Checking if initials are inside the last name
    initials_found = initials.upper() in last_name.upper()

    # Task 3: Count the number of digits in the string
    digits = digitsInName(last_name)

    # Display results
    print(f"1. The last name '{last_name}' has {syllable_count} syllables.")
    if initials_found:
        print(f"2. Your initials ({initials}) were found in the last name.")
    else:
        print(f"2. Your initials ({initials}) were not found in the last name.")  
    print(f'There are {digits} digits in your last name')

if __name__ == "__main__":
    last_name = input("Enter a last name: ")
    user_initials = input("Enter your initials: ")
    analyze_last_name(last_name, user_initials)
