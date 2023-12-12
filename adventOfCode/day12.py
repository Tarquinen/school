def main():
    print('test again')
    input = open('C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\adventOfCode\\day12.txt')
    inputLine = input.read().splitlines()
    
    # print first row of input
    # print(inputLine[0])

    for i in range(len(inputLine)):
        print(i + 1)
        print(inputLine[i])


    
if __name__ == "__main__":
    main()