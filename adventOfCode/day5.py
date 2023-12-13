
def part1(inputLines):
    l = len(inputLines)
    seeds = list(map(int, inputLines[0].split()[1:]))
    # print(f"seeds: {seeds}")

    inputMaps = []
    for i in range(1, l):
        if inputLines[i] and inputLines[i][0].isalpha():
            inputMaps.append(i)
    inputMaps.append(l + 1)
    # print(f"map locations: {inputMaps}")

    seedconverted = []
    for seed in seeds:
        for i in range(len(inputMaps) - 1):
            Map = getMap(inputMaps[i], inputMaps[i + 1], inputLines)
            for mapLine in Map:
                if seed >= mapLine[1] and seed < (mapLine[1] + mapLine[2]):
                    seed = seed - mapLine[1] + mapLine[0]
                    break
        seedconverted.append(seed)
        print(f"converted: {seedconverted}")
    print(f"lowest location: {min(seedconverted)}")
            
def getMap(mapStart, nextMap, inputLines):
    Map = []
    for i in range(mapStart + 1, nextMap - 1):
        Map.append(list(map(int, inputLines[i].split())))
    return Map



def main():
    input = open('C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\adventOfCode\\day5.txt')
    inputLines = input.read().splitlines()
    
    # # print input
    # for i in range(len(inputLine)):
    #     print(inputLine[i])

    part1(inputLines)
    
if __name__ == "__main__":
    main()