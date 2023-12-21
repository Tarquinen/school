import sys
import math

def part1(inputLines):
    instructions = inputLines[0]
    elementDict = {}
    for line in inputLines[2:]:
        element = line[0:3]
        left = line[7:10]
        right = line[12:15]
        # print(element, left, right)
        elementDict[element] = [left, right]
    # print(elementDict)

    stepCount = 0
    currentStep = 'AAA'
    notAtDestination = True
    while notAtDestination:
        for step in instructions:
            if step == 'L':
                currentStep = elementDict[currentStep][0] 
            else:
                currentStep = elementDict[currentStep][1] 
            stepCount += 1
            # print(f"current step: {currentStep}, step count: {stepCount}")
            if currentStep == 'ZZZ':
                notAtDestination = False
                break
    print(f"Total required steps: {stepCount}")

def part2(inputLines):
    instructions = inputLines[0]
    elementDict = {}
    for line in inputLines[2:]:
        element = line[0:3]
        left = line[7:10]
        right = line[12:15]
        # print(element, left, right)
        elementDict[element] = [left, right]
    # print(elementDict)
        
    notAtDestination = True
    nodes = []
    stepsToZ = []
    stepCount = 0
    for element in elementDict:
        if element[-1] == 'A':
            nodes.append(element)
    print(nodes)

    for i, node in enumerate(nodes):
        stepCount = 0
        notAtDestination = True
        print(node)
        while notAtDestination:
            for step in instructions:
                if step == 'L':
                    nodes[i] = elementDict[nodes[i]][0]
                    # print(f"went left to node {nodes[i]}")
                else:
                    nodes[i] = elementDict[nodes[i]][1]
                    # print(f"went right to node {nodes[i]}")
                stepCount += 1
                if nodes[i][-1] == 'Z':
                    print(f"step count: {stepCount}")
                    stepsToZ.append(stepCount)
                    notAtDestination = False
                    break
    print(f"Total required steps for each node: {stepsToZ}")

    LCM = stepsToZ[0]
    for step in stepsToZ:
        LCM = math.lcm(LCM, step)
    
    print(f"least common multiple of each node steps to reach a Z: {LCM}")

def main():
    input = open('C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\adventOfCode\\day8.txt')
    inputLines = input.read().splitlines()
    

    part2(inputLines)

if __name__ == "__main__":
    main()