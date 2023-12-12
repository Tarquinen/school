import math
import datetime


# define an exception for handling later if the user tries to input non normal grade values
class validGrade(Exception): 
    def __init__(self, message="Grade values can only be 0-100"):
        self.message = message
        super().__init__(self.message)

# function to check grade is a normal value (0 <= grade <= 100)
def validGradefunc(grade):
    if grade < 0 or grade > 100:
        raise validGrade('Enter a valid grade')


# This function will take a students name as argument as get input from the user for each one of their grades
def gradeInput(student): 
    print('Please enter the discussion, quiz and assignment grades for', student)
    while True: # only accepts float inputs
        try:
            discGrade = float(input('duscussion: ')) # this float stores the students discussion grade
            validGradefunc(discGrade) # raises an exception when the user inputs weird values
            quizGrade = float(input('quiz: ')) # this float stores the students quiz grade
            validGradefunc(quizGrade)
            assiGrade = float(input('assignment: ')) # this float stores the students assignment grade
            validGradefunc(assiGrade)
            break
        except validGrade:
            print('Grade values can only be 0-100, enter a valid grade')
        except ValueError:
            print('Invalid input. Please enter a valid number')

    gradeList = [discGrade, quizGrade, assiGrade] # creates an array with the students grades to more easily pass to other functions

    return gradeList # return the array of grades when this function is called


# This function calculates the students average grade
def gradeCalc(gradeList):
    avgGrade = gradeList[0] * .15 + gradeList[1] * .35 + gradeList[2] * .5 
    return avgGrade #returns the average grade

def main():
    print('Daniel Smolsky,', datetime.date.today(), end = ', CMSC 105\n')
    
    students = ['Dan', 'Steve', 'Mike', 'Alex'] # list of students attending the class

    highestGrade = 0 # initializes a variable to store the highest grade of the class
    bestStudent = '' # initializes a variable to store the name of the student with the highest grade

    for student in students: # This for loop cycles through each student
        gradeList = gradeInput(student) # This function will return the grades of each student in an array from user inputs [discussion, quiz, assignment]
        avgGrade = gradeCalc(gradeList) # this function will calculate the weighted average from the above array
        if avgGrade > highestGrade: # if this weighted average is the highest so far, it will store it to a variable along with the students name
            highestGrade = avgGrade
            bestStudent = student

    # Print the student with the highest grade and what their grade is
    print('The student with the highest grade is:', bestStudent, 'with a weighted average grade of', round(highestGrade, 2)) 


if __name__ == "__main__":
    main()