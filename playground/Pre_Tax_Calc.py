VIRGINIA_STANDARD_DEDUCTION = 4500
VIRGINIA_TAX_BRACKETS = [
   {"lower_bound": 0, "upper_bound": 3000, "rate": 0.02, "flat_charge": 0},
   {"lower_bound": 3000, "upper_bound": 5000, "rate": 0.03, "flat_charge": 60},
   {"lower_bound": 5000, "upper_bound": 17000, "rate": 0.05, "flat_charge": 120},
   {"lower_bound": 17000, "upper_bound": float('inf'), "rate": 0.0575, "flat_charge": 720}
]

FEDERAL_STANDARD_DEDUCTION = 14600
FEDERAL_TAX_BRACKETS = [
   {"lower_bound": 0, "upper_bound": 11600, "rate": 0.10},
   {"lower_bound": 11601, "upper_bound": 47150, "rate": 0.12},
   {"lower_bound": 47151, "upper_bound": 100525, "rate": 0.22},
   {"lower_bound": 100526, "upper_bound": 191950, "rate": 0.24},
   {"lower_bound": 191951, "upper_bound": 243725, "rate": 0.32},
   {"lower_bound": 243726, "upper_bound": 609350, "rate": 0.35},
   {"lower_bound": 609351, "upper_bound": float('inf'), "rate": 0.37}
]

MEDICARE_RATE = 0.0145
SOCIAL_SECURITY_RATE = 0.062

def calculate_income_post_tax(income):
   stateTax = 0
   federalTax = 0
   medicareTax = income * MEDICARE_RATE
   socialSecurityTax = income * SOCIAL_SECURITY_RATE
   stateIncomeAfterDeduction = income - VIRGINIA_STANDARD_DEDUCTION
   federalIncomeAfterDeduction = income - FEDERAL_STANDARD_DEDUCTION
   
   # calculate state income tax
   for bracket in VIRGINIA_TAX_BRACKETS:
      if stateIncomeAfterDeduction >= bracket["upper_bound"]:
         stateTax += bracket["flat_charge"] + ((bracket["upper_bound"] - bracket["lower_bound"]) * bracket["rate"])
      else:
         stateTax += bracket["flat_charge"] + ((stateIncomeAfterDeduction - bracket["lower_bound"]) * bracket["rate"])

   # calculate federal income tax
   for bracket in FEDERAL_TAX_BRACKETS:
      if federalIncomeAfterDeduction >= bracket["upper_bound"]:
         federalTax += (bracket["upper_bound"] - bracket["lower_bound"]) * bracket["rate"]
      elif federalIncomeAfterDeduction >= bracket["lower_bound"]:
         federalTax += (stateIncomeAfterDeduction - bracket["lower_bound"]) * bracket["rate"]

   return income - (stateTax + federalTax + medicareTax + socialSecurityTax)

def main():
   POST_TAX_INCOME = 100000
   preTaxIncomeGuess = POST_TAX_INCOME * 1.5 
   postTaxIncomeOfGuess = calculate_income_post_tax(preTaxIncomeGuess)
   while abs(postTaxIncomeOfGuess - POST_TAX_INCOME) > 1:
      ratio = postTaxIncomeOfGuess / POST_TAX_INCOME 
      preTaxIncomeGuess /= ratio
      postTaxIncomeOfGuess = calculate_income_post_tax(preTaxIncomeGuess)
      print(f"Guess: {preTaxIncomeGuess}, post tax: {postTaxIncomeOfGuess}")

   totalTaxesPaid = preTaxIncomeGuess - POST_TAX_INCOME
   totalTaxRate = totalTaxesPaid / preTaxIncomeGuess * 100
   
   print(f"In order to make {POST_TAX_INCOME} post taxes, yearly salary must be {int(preTaxIncomeGuess)}, "
         f"the taxes paid will be {int(totalTaxesPaid)} and the overall rate is {int(totalTaxRate)}%.")


if __name__ == "__main__":
   main()