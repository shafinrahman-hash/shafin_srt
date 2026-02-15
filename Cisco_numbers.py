def main():
    numbers = input("Enter the numbers: ")
    num = list(map(int, numbers.split()))
    sort = sorted(num)
    for i in sort:
        print(i)

if __name__ == "__main__":
    main()